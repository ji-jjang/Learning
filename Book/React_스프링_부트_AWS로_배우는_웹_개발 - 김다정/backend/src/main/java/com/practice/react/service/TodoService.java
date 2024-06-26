package com.practice.react.service;

import com.practice.react.model.TodoEntity;
import com.practice.react.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;
  public String testService() {
    TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
    todoRepository.save(entity);
    TodoEntity savedEntity = todoRepository.findById(entity.getId()).get();
    return savedEntity.getTitle();
  }

  public List<TodoEntity> create(final TodoEntity entity) {
    validate(entity);

    todoRepository.save(entity);

    log.info("Entity Id : {} is saved.", entity.getId());

    return todoRepository.findByUserId(entity.getUserId());
  }

  private static void validate(TodoEntity entity) {
    if (entity == null) {
      log.warn("Entity cannot be null.");
      throw new RuntimeException("Entity cannot be null.");
    }

    if (entity.getUserId() == null) {
      log.warn("Unknown user.");
      throw new RuntimeException("Unknown user.");
    }
  }

  public List<TodoEntity> retrieve(final String userId) {
    return todoRepository.findByUserId(userId);
  }

  public List<TodoEntity> update(final TodoEntity entity) {
    validate(entity);

    Optional<TodoEntity> original = todoRepository.findById(entity.getId());

    original.ifPresent(todo -> {
      todo.setTitle(entity.getTitle());
      todo.setDone(entity.isDone());

      todoRepository.save(todo);
    });

    return retrieve(entity.getUserId());
  }

  public List<TodoEntity> delete(final TodoEntity entity) {
    validate(entity);

    try {
      todoRepository.delete(entity);
    } catch (Exception e) {
      log.error("error deleting entity {}", entity.getId());
      throw new RuntimeException("error deleting entity " + entity.getId());
    }
    return retrieve(entity.getUserId());
  }
}
