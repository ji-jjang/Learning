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
@Table(name = "questions2")
@NoArgsConstructor
@Data
public class Question2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String text;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "question_choice2",
    joinColumns = @JoinColumn(name = "questions2_id")
  )
  @OrderColumn(name = "idx")
  private List<Choice> choices;
}
