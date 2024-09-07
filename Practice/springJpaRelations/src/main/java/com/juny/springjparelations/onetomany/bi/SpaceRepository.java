package com.juny.springjparelations.onetomany.bi;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface SpaceRepository extends JpaRepository<BiSpace, Long> {

}
