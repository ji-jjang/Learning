package com.juny.springjparelations.onetomany.bi;

import static org.assertj.core.api.Assertions.*;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MySQLDBCustomerTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private SpaceRepository spaceRepository;

  @Autowired
  private EntityManager em;
@Test
@Transactional
@Rollback(false)
@DisplayName("Mysql DB에 데이터 삽입")
public void makeDummyData() {
  BiCustomer biCustomer = new BiCustomer("juny");
  BiSpace biSpace1 = new BiSpace("주니의 공간1");
  BiSpace biSpace2 = new BiSpace("주니의 공간2");

  biSpace1.setBiCustomer(biCustomer);
  biSpace2.setBiCustomer(biCustomer);

  customerRepository.save(biCustomer);

  spaceRepository.save(biSpace1);
  spaceRepository.save(biSpace2);

}

  @Test
  @Transactional
  @DisplayName("DB에 저장된 데이터를 조회할 때, JPA 연관관계는 자동으로 구성")
  public void getSpaces() {

    BiSpace space = spaceRepository.findById(1L)
      .orElseThrow(() -> new RuntimeException("해당 아이디인 공간이 존재하지 않습니다."));

    BiCustomer customer =
      customerRepository
        .findById(1L)
        .orElseThrow(() -> new RuntimeException("해당 아이디인 고객이 존재하지 않습니다."));

    assertThat(space.getName()).isEqualTo("주니의 공간1");
    assertThat(customer.getBiSpaces().size()).isEqualTo(2);
  }
}
// spaceRepository를 먼저 저장하고, customerRepository를 저장하는 것과 무슨 차이가 있을까?
// 추가적인 Update 쿼리가 발생한다!!
// insert into customers_bi (name) values ('juny');
// update spaces_bi set bi_customer_id=2,name='주니의 공간1' where id=3;
// update spaces_bi set bi_customer_id=2,name='주니의 공간2' where id=4;

// insert into customers_bi (name) values ('juny');
// insert into spaces_bi (bi_customer_id,name) values (3,'주니의 공간1');
// insert into spaces_bi (bi_customer_id,name) values (3,'주니의 공간2');


