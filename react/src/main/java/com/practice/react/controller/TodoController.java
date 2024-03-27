package com.practice.react.controller;

import com.practice.react.dto.ResponseDTO;
import com.practice.react.dto.TodoDTO;
import com.practice.react.model.TodoEntity;
import com.practice.react.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoController {

  @Autowired
  private TodoService service;

  @GetMapping("/todo/test")
  public ResponseEntity<?> testTodo() {
    String str = service.testService();
    List<String> list = new ArrayList<>();
    list.add(str);
    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/todo")
  public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
    try {
      String temporaryUserId = "temporary-user";

      TodoEntity entity = TodoDTO.toEntity(dto);

      entity.setId(null);

      entity.setUserId(temporaryUserId);

      List<TodoEntity> entities = service.create(entity);

      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).toList();

      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      String error = e.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping("/todo")
  public ResponseEntity<?> retrieveTodoList() {
    String temporaryUserId = "temporary-user";

    List<TodoEntity> entities = service.retrieve(temporaryUserId);

    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).toList();

    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

    return ResponseEntity.ok().body(response);
  }

  @PutMapping("/todo")
  public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
    String temporaryUserId = "temporary-user";

    TodoEntity entity = TodoDTO.toEntity(dto);

    entity.setUserId(temporaryUserId);

    List<TodoEntity> entities = service.update(entity);
    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).toList();
    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/todo")
  public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
    try {
      String temporaryUserId = "temporary-user";
      TodoEntity entity = TodoDTO.toEntity(dto);
      entity.setUserId(temporaryUserId);
      List<TodoEntity> entities = service.delete(entity);
      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).toList();

      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

      return ResponseEntity.ok().body(response);

    } catch (Exception e) {
      String error = e.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }
}