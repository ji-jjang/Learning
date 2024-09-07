package com.juny.springjparelations.onetomany.uni;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CustomerTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private SpaceRepository spaceRepository;

@Test
@Transactional
@Rollback(false)
public void createCustomerTest() {
  UniCustomer uniCustomer = new UniCustomer("juny");
  UniSpace uniSpace1 = new UniSpace("주니의 공간1");
  UniSpace uniSpace2 = new UniSpace("주니의 공간1");

  spaceRepository.save(uniSpace1);
  spaceRepository.save(uniSpace2);

  uniCustomer.addSpace(uniSpace1);
  uniCustomer.addSpace(uniSpace2);

  customerRepository.save(uniCustomer);
  }
}
// insert into customers (name) values ('juny');
// update spaces set customer_id=2 where id=1;
// update spaces set customer_id=2 where id=2;
