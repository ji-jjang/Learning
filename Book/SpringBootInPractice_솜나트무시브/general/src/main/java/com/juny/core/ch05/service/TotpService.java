package com.juny.core.ch05.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.juny.core.ch05.exception.InvalidVerificationCode;
import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.model.TotpDetails;
import com.juny.core.ch05.repository.ApplicationUserRepository;
import com.juny.core.ch05.repository.TotpRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import jakarta.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

@Service
public class TotpService {

  private final GoogleAuthenticator googleAuth = new GoogleAuthenticator();
  private final TotpRepository totpRepository;
  private final ApplicationUserRepository userRepository;
  private static final String ISSUER = "CourseTracker";


  public TotpService(TotpRepository totpRepository, ApplicationUserRepository userRepository) {
    this.totpRepository = totpRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public String generateAuthenticationQrUrl(String username) throws WriterException, IOException {
    GoogleAuthenticatorKey authenticationKey = googleAuth.createCredentials();
    String secret = authenticationKey.getKey();
    totpRepository.deleteByUsername(username);
    totpRepository.save(new TotpDetails(username, secret));
    String otpAuthTotpURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(ISSUER, username,
        authenticationKey);
    System.out.println("otpAuthTotpURL = " + otpAuthTotpURL);

    // Generate QR Code image
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(otpAuthTotpURL, BarcodeFormat.QR_CODE, 200, 200);
    BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

    // Convert to Base64
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(qrImage, "PNG", baos);
    byte[] imageBytes = baos.toByteArray();
    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
    // Return Base64-encoded image URI

    return "data:image/png;base64," + base64Image;
  }

  public boolean isTotpEnabled(String username) {
    return userRepository.findByUsername(username).isTotpEnabled();
  }

  public void enableTotpForUser(String username, int code) {
    if (!verifyCode(username, code)){
      throw new InvalidVerificationCode("Invalid verification code");
    }
    ApplicationUser user = userRepository.findByUsername(username);
    user.setTotpEnabled(true);
    userRepository.save(user);
  }

  public boolean verifyCode(String username, int verificationCode) {
    TotpDetails totpDetails = totpRepository.findByUsername(username);
    return googleAuth.authorize(totpDetails.getSecret(), verificationCode);
  }
}
