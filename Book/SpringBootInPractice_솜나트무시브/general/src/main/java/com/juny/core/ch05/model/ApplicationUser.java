package com.juny.core.ch05.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "CT_USERS")
@Data
@ToString
public class ApplicationUser {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String password;
  private boolean verified;
  private boolean locked;
  @Column(name = "ACC_CRED_EXPIRED")
  private boolean accountCredentialsExpired;

  private boolean totpEnabled;

}
