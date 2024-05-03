package com.juny.core.ch05.service;

import com.juny.core.ch05.model.Course;
import com.juny.core.ch05.repository.CourseRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCourseService implements CourseService {
  private CourseRepository courseRepository;

  @Autowired
  public DefaultCourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public Optional<Course> findCourseById(Long courseId) {
    return courseRepository.findById(courseId);
  }

  public Iterable<Course> findAllCourses() {
    return courseRepository.findAll();
  }

  public Course updateCourse(Course course) {
    return courseRepository.save(course);
  }

  public void deleteCourseById(Long courseId) {
    courseRepository.deleteById(courseId);
  }
}
