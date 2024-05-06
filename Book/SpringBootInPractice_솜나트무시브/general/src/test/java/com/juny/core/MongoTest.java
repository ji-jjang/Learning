package com.juny.core;


//import com.mongodb.BasicDBObjectBuilder;
//import com.mongodb.DBObject;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//public class MongoTest {
//
//  @Autowired
//  private MongoTemplate mongoTemplate;
//
//  @Test
//  public void givenObjectAvaliableWhenSaveToCollectionThenExpectValue() {
//    DBObject object = BasicDBObjectBuilder.start().add("Manning", "Spring Boot In Practice").get();
//
//    mongoTemplate.save(object, "collection");
//
//    Assertions.assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
//        .extracting("Manning")
//        .contains("Spring Boot In Practice");
//  }
//}
