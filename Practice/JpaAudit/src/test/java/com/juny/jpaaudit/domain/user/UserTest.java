package com.juny.jpaaudit.domain.user;

import com.juny.jpaaudit.config.TestAuditAwareConfig;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void createUser() {

    User user2 = new User("user2", "1234");
    User savedUser = userRepository.save(user2);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void updateUser() throws BadRequestException {
    User user = userRepository.findById(2L)
      .orElseThrow(() -> new BadRequestException("not exist user"));

    user.updateName("new new user!!");
  }

  @Test
  @Transactional
  @Rollback(false)
  public void deleteUser() throws BadRequestException {

    User user = userRepository.findById(2L)
      .orElseThrow(() -> new BadRequestException("not exist user"));

    user.softDelete();
  }
}
