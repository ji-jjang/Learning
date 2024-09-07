package com.example.valueobjectcollection.list;

import com.example.valueobjectcollection.set.Role;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class QuestionTest {

  @Autowired QuestionRepository questionRepository;

@Test
@Rollback(value = false)
void saveQuestion() {

  Question question = new Question("내용 1", List.of("보기 1", "보기 2", "보기 3"));
  questionRepository.save(question);
}
// insert into questions (text) values ('내용 1');
// insert into question_choice (question_id,idx,test) values (1,0,'보기 1');
// insert into question_choice (question_id,idx,test) values (1,1,'보기 2');
// insert into question_choice (question_id,idx,test) values (1,2,'보기 3');


@Test
@Transactional
void findQuestionById() {

  Question question = questionRepository.findById(1L).get();

  for (var choice : question.getChoices()) {
    System.out.println("choice = " + choice);
  }
}
// Lazy
// select q1_0.id,q1_0.text from questions q1_0 where q1_0.id=1;
// select c1_0.question_id,c1_0.idx,c1_0.test from question_choice c1_0 where c1_0.question_id=1;

// Eager
// select q1_0.id,c1_0.question_id,c1_0.idx,c1_0.test,q1_0.text from questions q1_0 left join question_choice c1_0 on q1_0.id=c1_0.question_id where q1_0.id=1;

@Test
@Rollback(value = false)
@Transactional
void addAndRemoveChoice() {

  Question question = questionRepository.findById(1L).get();

  question.getChoices().add("보기 4");
  question.getChoices().remove("보기 1");
}
// add 하고, remove을 별도로 실행했을 경우
// insert into question_choice (question_id,idx,test) values (1,3,'보기 4');
// delete from question_choice where question_id=1 and idx=3;
// update question_choice set test='보기 4' where question_id=1 and idx=2;
// update question_choice set test='보기 3' where question_id=1 and idx=1;
// update question_choice set test='보기 2' where question_id=1 and idx=0;

// add와 remove를 동시에 실행했을 경우
// update question_choice set test='보기 4' where question_id=1 and idx=2;
// update question_choice set test='보기 3' where question_id=1 and idx=1;
// update question_choice set test='보기 2' where question_id=1 and idx=0;
}
