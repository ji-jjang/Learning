package com.example.valueobjectcollection.map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "docs2")
@NoArgsConstructor
@Data
public class Document2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String content;

  @ElementCollection
  @CollectionTable(
    name = "doc_prop2",
    joinColumns = @JoinColumn(name = "docs2_id"))
  @MapKeyColumn(name = "name")
  @Column(name = "value")
  private Map<String, PropValue> props = new HashMap<>();
}
