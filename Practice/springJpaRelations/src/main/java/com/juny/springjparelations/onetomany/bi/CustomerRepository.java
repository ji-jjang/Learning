package com.juny.springjparelations.onetomany.bi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryBi")
public interface CustomerRepository extends JpaRepository<BiCustomer, Long> {

}
