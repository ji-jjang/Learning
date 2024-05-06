package com.juny.core.ch02.custom_bean_validtation;

import com.juny.core.ch02.Course;
import com.juny.core.ch02.custom_bean_validtation.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Order(1)
@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    log.info("MyCommandLineRunner executed as a Spring Compoent");

//    Course course = new Course();
//    course.setId(1L);
//    course.setRating(0);
//
//    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//    Set<ConstraintViolation<Course>> violations0 = validator.validate(course);
//
//    violations0.forEach(v ->
//        log.error("A constraint violation has occured. Violation details: [{}].", v));
//
//    User user1 = new User("sbip01", "sbip");
//    Set<ConstraintViolation<User>> violations = validator.validate(user1);
//    violations.forEach(v -> log.error("user1 Violoation details: [{}].", v.getMessage()));
//
//    User user2 = new User("sbip02", "Sbip01$4UDfg");
//    violations = validator.validate(user2);
//    if (violations.isEmpty()) {
//      log.info("Password for user2 adhere to the password policy.");
//    }
//
//    User user3 = new User("sbip03", "Sbip01$4UDfgggg");
//    violations = validator.validate(user3);
//    violations.forEach(v -> log.error("user3 Violoation details: [{}].", v.getMessage()));
//
//    User user4 = new User("sbip04", "SS932");
//    violations = validator.validate(user4);
//    System.out.println("violations = " + violations);
//    violations.forEach(v -> log.error("user4 Violoation details: [{}].", v.getMessage()));
  }
}
