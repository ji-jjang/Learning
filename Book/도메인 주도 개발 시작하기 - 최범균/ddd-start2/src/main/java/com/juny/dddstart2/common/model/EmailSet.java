package com.juny.dddstart2.common.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class EmailSet {
  private Set<Email> emails = new HashSet<>();

  public EmailSet(Set<Email> emails) {
    this.emails = emails;
  }

  public Set<Email> getEmails() {
    return Collections.unmodifiableSet(emails);
  }
}
