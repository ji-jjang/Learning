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

    @Component
    class JpaRunner {

        private final TransactionalLogic transactionalLogic;

        public JpaRunner(TransactionalLogic transactionalLogic) {
            this.transactionalLogic = transactionalLogic;
        }

        @PostConstruct
        public void init() {
            transactionalLogic.run();
        }

        @Component
        public static class TransactionalLogic {

            @PersistenceContext
            private EntityManager em;

            @Transactional
            public void run() {
                logic(em);
            }

            private void logic(EntityManager em) {
                Long id = 1L;
                Member member = new Member();
                member.setUsername("주니");
                member.setAge(20);

                em.persist(member);

                member.setAge(50);

                Member findMember = em.find(Member.class, id);
                System.out.println("findMember.getId() = " + findMember.getId());
                System.out.println("findMember.getUsername() = " + findMember.getUsername());
                System.out.println("findMember.getAge() = " + findMember.getAge());

                List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
                System.out.println("members.size() = " + members.size());

                em.remove(member);
            }
        }
    }
}