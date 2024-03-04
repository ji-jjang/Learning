package com.jpa.javaormstandardjpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ExamMergeMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember("memberA", 5L);
        member.setUsername("memberModify");
        mergeMember(member);
    }

    private static void mergeMember(Member member) {
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        
        tx2.begin();
        Member mergeMember = em2.merge(member);
        tx2.commit();

        System.out.println("member.getUsername() = " + member.getUsername());

        System.out.println("mergeMember.getUsername() = " + mergeMember.getUsername());

        System.out.println("em2.contains(member) = " + em2.contains(member));

        System.out.println("em2.contains(mergeMember) = " + em2.contains(mergeMember));

        em2.close();
    }

    private static Member createMember(String username, Long age) {
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setUsername(username);
        member.setId(age);

        em1.persist(member);
        tx1.commit();

        em1.close();

        return member;
    }
}
