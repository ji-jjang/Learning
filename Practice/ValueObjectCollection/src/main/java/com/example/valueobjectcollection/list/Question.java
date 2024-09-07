package com.example.valueobjectcollection.list;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@Data
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String text;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "question_choice",
    joinColumns = @JoinColumn(name = "question_id")
  )
  @OrderColumn(name = "idx")
  @Column(name = "test")
  private List<String> choices;

  public Question(String text, List<String> choices) {
    this.text = text;
    this.choices = choices;
  }
}
