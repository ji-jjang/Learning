package com.example.valueobjectcollection.set;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class RoleTest {


  @Autowired
  private RoleRepository roleRepository;

  @Test
  @Rollback(value = false)
  void saveRole() {
    Role role = new Role("관리자", Set.of("권한 1", "권한 2", "권한 3"));

    roleRepository.save(role);

    //    insert into roles (name,id) values ('관리자',default);
    //    insert into role_perm (role_id,perm) values (1,'권한 3');
    //    insert into role_perm (role_id,perm) values (1,'권한 2');
    //    insert into role_perm (role_id,perm) values (1,'권한 1');
  }

  @Test
  @Transactional
  void findRoleById() {

    Role role = roleRepository.findById(1L).get();

    for (var perm : role.getPermissions()) {
      System.out.println("perm = " + perm);
    }
  }

  // select r1_0.id,r1_0.name from roles r1_0 where r1_0.id=1;
  // select p1_0.role_id,p1_0.perm from role_perm p1_0 where p1_0.role_id=1;

  // @ElementCollection(fetch = FetchType.EAGER)
  // Eager 방식: select r1_0.id,r1_0.name,p1_0.role_id,p1_0.perm from roles r1_0 left join role_perm
  // p1_0 on r1_0.id=p1_0.role_id where r1_0.id=1;

  @Test
  @Rollback(value = false)
  @Transactional
  void addAndRemovePermission() {

    Role role = roleRepository.findById(1L).get();

    role.getPermissions().add("권한 4");
    role.getPermissions().remove("권한 1");
  }
  // delete from role_perm where role_id=1 and perm='권한 1';
  // insert into role_perm (role_id,perm) values (1,'권한 4');
}
