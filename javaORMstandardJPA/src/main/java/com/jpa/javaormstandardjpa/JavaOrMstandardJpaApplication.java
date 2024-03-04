package com.jpa.javaormstandardjpa;

import com.jpa.javaormstandardjpa.domain.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class JavaOrMstandardJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaOrMstandardJpaApplication.class, args);
    }
}