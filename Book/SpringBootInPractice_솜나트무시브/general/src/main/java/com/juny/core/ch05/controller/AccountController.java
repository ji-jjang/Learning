package com.juny.core.ch05.controller;

import com.google.zxing.WriterException;
import com.juny.core.ch05.exception.InvalidVerificationCode;
import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.model.CustomUser;
import com.juny.core.ch05.model.TotpCode;
import com.juny.core.ch05.service.TotpService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

  private final TotpService totpService;

  public AccountController(TotpService totpService) {
    this.totpService = totpService;
  }

  @GetMapping("/account")
  public String account(Model model, @AuthenticationPrincipal CustomUser customUser) {
    if (customUser != null && !customUser.isTotpEnabled()){
      model.addAttribute("totpEnabled", false);
      model.addAttribute("configureTotp", true);
    } else {
      model.addAttribute("totpEnabled", true);
    }

    return "account";
  }

  @GetMapping("/setup-totp")
  public String getGoogleAuthenticatorQrUr(
      Model model, @AuthenticationPrincipal CustomUser customUser)
      throws IOException, WriterException {
    String username = customUser.getUsername();
    boolean isTotp = customUser.isTotpEnabled();

    if (!isTotp){
      model.addAttribute("qrUrl", totpService.generateAuthenticationQrUrl(username));
      model.addAttribute("code", new TotpCode());
      return "account";
    }

    model.addAttribute("totpEnabled", true);
    return "account";
  }

  @PostMapping("/confirm-totp")
  public String confirmGoogleAuthenticatorSetup(
      Model model, @AuthenticationPrincipal CustomUser customUser, TotpCode totpCode) {

    boolean isTotp = customUser.isTotpEnabled();

    if (customUser.isTotpEnabled()) {
      model.addAttribute("message", "2FA is already enabled.");
      return "error";
    }

    if (!isTotp){
      try {
        totpService.enableTotpForUser(
            customUser.getUsername(), Integer.valueOf(totpCode.getCode())
        );
      } catch (InvalidVerificationCode ex){
        model.addAttribute("totpEnabled", customUser.isTotpEnabled());
        model.addAttribute("confirmError", true);
        model.addAttribute("configureTotp", false);
        model.addAttribute("code", new TotpCode());
        return "account";
      }
      model.addAttribute("totpEnabled", true);
    }

    customUser.setTotpEnabled(true);
    return "redirect:/success-totp-setup";
  }

  @Controller
  public class SetupController {

    @GetMapping("/success-totp-setup")
    public String successSetup() {
      return "success-totp-setup";
    }
  }
}
