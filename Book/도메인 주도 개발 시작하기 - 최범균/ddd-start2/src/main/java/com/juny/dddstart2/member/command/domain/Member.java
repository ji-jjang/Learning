package com.juny.dddstart2.member.command.domain;

import com.juny.dddstart2.common.event.Events;
import com.juny.dddstart2.common.jpa.EmailSetConverter;
import com.juny.dddstart2.common.model.Email;
import com.juny.dddstart2.common.model.EmailSet;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {
  @EmbeddedId
  private MemberId id;

  private String name;
  @Embedded
  private Password password;

  private boolean blocked;

  @Column(name = "emails")
  @Convert(converter = EmailSetConverter.class)
  private EmailSet emails;

  protected Member() {
  }

  public Member(MemberId id, String name) {
    this.id = id;
    this.name = name;
  }

  public MemberId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void initializePassword() {
    String newPassword = generateRandomPassword();
    this.password = new Password(newPassword);
    Events.raise(new PasswordChangedEvent(id.getId(), newPassword));
  }

  private String generateRandomPassword() {
    Random random = new Random();
    int number = random.nextInt();
    return Integer.toHexString(number);
  }

  public void changeEmails(Set<Email> emails) {
    this.emails = new EmailSet(emails);
  }

  public void block() {
    this.blocked = true;
    Events.raise(new MemberBlockedEvent(id.getId()));
  }

  public void unblock() {
    this.blocked = false;
    Events.raise(new MemberUnblockedEvent(id.getId()));
  }

  public void changePassword(String oldPw, String newPw) {
    if (!password.match(oldPw)) {
      throw new IdPasswordNotMatchingException();
    }
    this.password = new Password(newPw);
    Events.raise(new PasswordChangedEvent(id.getId(), newPw));
  }

  public boolean isBlocked() {
    return blocked;
  }

  public EmailSet getEmails() {
    return emails;
  }
}
