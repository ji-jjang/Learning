package com.juny.dddstart2.order.infra;

import com.juny.dddstart2.member.command.domain.MemberId;
import com.juny.dddstart2.member.query.MemberData;
import com.juny.dddstart2.member.query.MemberQueryService;
import com.juny.dddstart2.order.command.domain.OrdererService;
import com.juny.dddstart2.order.command.domain.Orderer;
import org.springframework.stereotype.Service;

@Service
public class OrdererServiceImpl implements OrdererService {
  private MemberQueryService memberQueryService;

  public OrdererServiceImpl(MemberQueryService memberQueryService) {
    this.memberQueryService = memberQueryService;
  }

  @Override
  public Orderer createOrderer(MemberId ordererMemberId) {
    MemberData memberData = memberQueryService.getMemberData(ordererMemberId.getId());
    return new Orderer(MemberId.of(memberData.getId()), memberData.getName());
  }

}
