package com.juny.dddstart2.member.query;

import com.juny.dddstart2.common.jpa.Rangeable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

public interface MemberDataDao extends Repository<MemberData, Long> {
  MemberData findById(String memberId);

  Page<MemberData> findByBlocked(boolean blocked, Pageable pageable);
  List<MemberData> findByNameLike(String name, Pageable pageable);

  List<MemberData> findAll(Specification<MemberData> spec, Pageable pageable);

  List<MemberData> getRange(Specification<MemberData> spec, Rangeable rangeable);

  List<MemberData> findFirst3ByNameLikeOrderByName(String name);
  Optional<MemberData> findFirstByNameLikeOrderByName(String name);
  MemberData findFirstByBlockedOrderById(boolean blocked);
}
