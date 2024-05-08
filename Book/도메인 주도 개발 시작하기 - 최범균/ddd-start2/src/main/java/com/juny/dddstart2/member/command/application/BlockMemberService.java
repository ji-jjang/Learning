package com.juny.dddstart2.member.command.application;

import com.juny.dddstart2.member.command.domain.Member;
import com.juny.dddstart2.member.command.domain.MemberId;
import com.juny.dddstart2.member.command.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockMemberService {
  private MemberRepository memberRepository;

  public BlockMemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

//  @PreAuthorize("hasRole('ADMIN')")
  @Transactional
  public void block(String memberId) {
    Member member = memberRepository.findById(new MemberId(memberId))
        .orElseThrow(() -> new NoMemberException());

    member.block();
  }
}
