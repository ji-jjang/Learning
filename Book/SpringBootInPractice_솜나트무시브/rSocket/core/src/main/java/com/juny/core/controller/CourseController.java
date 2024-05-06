package com.juny.core.controller;

import com.juny.core.model.Course;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class CourseController {

  // ./rsc --fnf --route=fire-and-forget --dataMimeType=application/json --data='{"courseName":"Java 101"}' tcp://localhost:9000
  @MessageMapping("request-response")
  public Mono<Course> requestResponse(Course course) {
    log.info("Received request-response course details {} ", course);
    return Mono.just(new Course("Your course name: " + course.getCourseName()));
  }

  // ./rsc --fnf --route=fire-and-forget --dataMimeType=application/json --data='{"courseName":"Java 101"}' tcp://localhost:9000
  @MessageMapping("fire-and-forget")
  public Mono<Void> fireAndForget(final Course course) {
    log.info("Received fire-and-forget course details {} ", course);
    return Mono.empty();
  }

  // ./rsc --stream --route=request-stream --dataMimeType=application/json --data='{"courseName":"Java 101"}' tcp://localhost:9000
  @MessageMapping("request-stream")
  public Flux<Course> requestStream(final Course course) {
    log.info("Received request-stream course details {} ", course);
    return Flux.interval(Duration.ofSeconds(1))
        .map(index -> new Course("Your course name: " + course.getCourseName() + ". Response #" + index))
        .log();
  }

  //echo -e '1\n2' | ./rsc --channel --route=stream-stream --dataMimeType=application/json --data - tcp://localhost:9000 --stacktrace
  @MessageMapping("stream-stream")
  public Flux<Course> channel(final Flux<Integer> settings) {
    log.info("Received stream-stream (channel) request... ");

    return settings.doOnNext(setting -> log.info("Requested interval is {} seconds", setting))
        .doOnCancel(() -> log.warn("Client cancelled the channel"))
        .switchMap(setting -> Flux.interval(Duration.ofSeconds(setting)).map(index -> new Course("Spring. Response #"+index)))
        .log();
  }
}
