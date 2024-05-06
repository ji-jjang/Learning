package com.juny.core.ch05.service;

import com.juny.core.ch05.model.EmailVerification;
import com.juny.core.ch05.repository.EmailVerificationRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {

  private final EmailVerificationRepository repository;

  public EmailVerificationService(EmailVerificationRepository repository) {
    this.repository = repository;
  }

  public String generateVerification(String username) {
    if (!repository.existsByUsername(username)) {
      EmailVerification verification = new EmailVerification(username);
      verification = repository.save(verification);
      return verification.getVerificationId();
    }
    return getVerificationIdByUsername(username);
  }

  private String getVerificationIdByUsername(String username) {
    EmailVerification verification = repository.findByUsername(username);
    if (verification != null){
      return verification.getVerificationId();
    }
    return null;
  }

  public String getUsernameForVerificationId(String verificationId) {
    Optional<EmailVerification> verification = repository.findById(verificationId);
    if (verification.isPresent()) {
      return verification.get().getUsername();
    }
    return null;
  }
}