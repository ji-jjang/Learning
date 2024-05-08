package com.juny.dddstart2.order.command.domain;

import com.juny.dddstart2.member.command.domain.MemberId;

public interface OrdererService {
  Orderer createOrderer(MemberId ordererMemberId);
}
