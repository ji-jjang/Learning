package com.juny.dddstart2.member.command.domain;

import com.juny.dddstart2.common.event.Event;

public class MemberBlockedEvent extends Event {
  private String memberId;

  public MemberBlockedEvent(String memberId) {
    this.memberId = memberId;
  }

  public String getMemberId() {
    return memberId;
  }
}
