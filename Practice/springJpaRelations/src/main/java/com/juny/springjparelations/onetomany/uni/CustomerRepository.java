package com.juny.springjparelations.onetomany.uni;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<UniCustomer, Long> {

}
