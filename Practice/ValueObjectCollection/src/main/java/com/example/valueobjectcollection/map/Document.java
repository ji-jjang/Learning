package com.example.valueobjectcollection.map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "docs")
@NoArgsConstructor
@Data
public class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String content;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "doc_prop",
    joinColumns = @JoinColumn(name = "doc_id"))
  @MapKeyColumn(name = "name")
  @Column(name = "value")
  private Map<String, String> props = new HashMap<>();

  public Document(String title, String content, Map<String, String> props) {
    this.title = title;
    this.content = content;
    this.props = props;
  }
}
