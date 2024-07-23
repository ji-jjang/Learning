package com.example.mybatis.user.repository.mybatis;

import com.example.mybatis.user.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("mybatisUserMapper")
public interface UserMapper {
  List<User> findAll();
}
