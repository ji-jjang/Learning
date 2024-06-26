package com.practice.react.persistence;

import com.practice.react.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {

  @Query("select t from TodoEntity t where t.userId = ?1")
  List<TodoEntity> findByUserId(String userId);
}
