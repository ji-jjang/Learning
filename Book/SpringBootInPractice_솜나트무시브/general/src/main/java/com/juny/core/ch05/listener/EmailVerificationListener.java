package com.juny.core.ch05.listener;

import com.juny.core.ch05.event.UserRegistrationEvent;
import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.service.EmailVerificationService;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

  private final JavaMailSender mailSender;

  private final EmailVerificationService verificationService;

  public EmailVerificationListener(JavaMailSender mailSender,
      EmailVerificationService verificationService) {
    this.mailSender = mailSender;
    this.verificationService = verificationService;
  }

  @Override
  public void onApplicationEvent(UserRegistrationEvent event) {
    System.out.println("EmailVerificationListener.onApplicationEvent");
    ApplicationUser user = event.getApplicationUser();
    String username = user.getUsername();
    String verificationId = verificationService.generateVerification(username);
    String email = event.getApplicationUser().getEmail();

    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Course Tracker Account Verification");
    message.setText(getText(user, verificationId));
    message.setTo(email);
    mailSender.send(message);
  }

  private String getText(ApplicationUser user, String verificationId) {
    System.out.println("EmailVerificationListener.getText");
    String encodedVerificationId = new String(Base64.getEncoder().encode(verificationId.getBytes()));
    StringBuffer buffer = new StringBuffer();
    buffer.append("Dear ").append(user.getFirstName()).append(" ").append(user.getLastName()).append(",").append((System.lineSeparator())).append(System.lineSeparator());
    buffer.append("Your account has been successfully created in the Course Tracker application. ");
    buffer.append("Activate your account by clicking the follwing link: http://localhost:8080/verify/email?id=").append(encodedVerificationId);
    buffer.append(System.lineSeparator()).append(System.lineSeparator());
    buffer.append("Regards,").append(System.lineSeparator()).append("Course Tracker Team");
    return buffer.toString();
  }

  @Override
  public boolean supportsAsyncExecution() {
    return ApplicationListener.super.supportsAsyncExecution();
  }
}
