package com.juny.springjparelations.onetomany.bi;

import static org.assertj.core.api.Assertions.*;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ActiveProfiles("test")
public class InMemoryCustomerUnitTest {

  @Autowired
  private SpaceRepository spaceRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private EntityManager em;

  @Test
  @DisplayName("양방향 관계 설정이 누락되면 객체 일관성 깨짐")
  public void createCustomerTest() {
    BiCustomer customer = new BiCustomer("customer1");
    BiSpace space1 = new BiSpace("space1");
    BiSpace space2 = new BiSpace("space2");
    space1.setBiCustomer(customer);
    space2.setBiCustomer(customer);

//    customer.getBiSpaces().add(space1);
//    customer.getBiSpaces().add(space2);

    assertThat(space1.getBiCustomer().getName()).isEqualTo(customer.getName());
    assertThat(space2.getBiCustomer().getName()).isEqualTo(customer.getName());

    assertThat(customer.getBiSpaces()).hasSize(2);
    // Expected size: 2 but was: 0 in:
  }

  @Test
  @Transactional
  @DisplayName("양방향 관계 설정이 누락되어도 영속성 컨텍스트에 등록되었다면 자유롭게 객채 그래프 탐색 가능")
  public void lookUpInPersistContext() {
    BiCustomer customer = new BiCustomer("customer1");
    BiSpace space1 = new BiSpace("space1");
    BiSpace space2 = new BiSpace("space2");
    space1.setBiCustomer(customer);
    space2.setBiCustomer(customer);

    BiCustomer savedCustomer = customerRepository.save(customer);
    spaceRepository.save(space1);
    spaceRepository.save(space2);

    em.clear();

    BiCustomer foundCustomer = customerRepository.findById(savedCustomer.getId()).get();
    assertThat(foundCustomer.getName()).isEqualTo(customer.getName());
    assertThat(foundCustomer.getBiSpaces().size()).isEqualTo(2);
  }
}
