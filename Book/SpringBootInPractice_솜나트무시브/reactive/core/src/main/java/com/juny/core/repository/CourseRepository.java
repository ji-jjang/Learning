package com.juny.core.repository;

import com.juny.core.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
  Flux<Course> findAllByCategory(String category);
}
