package com.juny.springjparelations.onetomany.uni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryUni")
public interface SpaceRepository extends JpaRepository<UniSpace, Long> {

}
