package com.example.valueobjectcollection.map;

import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class DocumentTest {

  @Autowired private  DocumentRepository documentRepository;

@Test
@Rollback(value = false)
public void saveDoc() {

  Map<String, String> props = new HashMap<>();
  props.put("props1", "value1");
  props.put("props2", "value2");
  Document doc = new Document("제목 1", "내용 1", props);
  documentRepository.save(doc);
}
// insert into docs (content,title) values ('내용 1','제목 1');
// insert into doc_prop (doc_id,name,value) values (1,'props1','value1');
// insert into doc_prop (doc_id,name,value) values (1,'props2','value2');

@Test
@Transactional
void findQuestionById() {

  Document document = documentRepository.findById(1L).get();

  for (var prop : document.getProps().entrySet()) {
    System.out.println(prop.getKey() + ": " + prop.getValue());
  }
}
// select d1_0.id,d1_0.content,d1_0.title from docs d1_0 where d1_0.id=1;
// select p1_0.doc_id,p1_0.name,p1_0.value from doc_prop p1_0 where p1_0.doc_id=1;

@Test
@Rollback(value = false)
@Transactional
void addAndRemoveProp() {

  Document document = documentRepository.findById(1L).get();

  document.getProps().put("props3", "value3");
  document.getProps().remove("props1");
}
// fetch
// select d1_0.id,d1_0.content,d1_0.title from docs d1_0 where d1_0.id=1;
// select p1_0.doc_id,p1_0.name,p1_0.value from doc_prop p1_0 where p1_0.doc_id=1;
// delete from doc_prop where doc_id=1 and name='props1';
// insert into doc_prop (doc_id,name,value) values (1,'props3','value3');

// eager
// select d1_0.id,d1_0.content,p1_0.doc_id,p1_0.name,p1_0.value,d1_0.title from docs d1_0 left join doc_prop p1_0 on d1_0.id=p1_0.doc_id where d1_0.id=1;
// delete from doc_prop where doc_id=1 and name='props1';
// insert into doc_prop (doc_id,name,value) values (1,'props3','value3');
}
