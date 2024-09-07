package com.example.mybatis.user.repository.jpa;

import com.example.mybatis.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
