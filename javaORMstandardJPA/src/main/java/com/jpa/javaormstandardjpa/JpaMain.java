package com.jpa.javaormstandardjpa;

import com.jpa.javaormstandardjpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // [엔티티 매니저 팩토리] 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        // [엔티티 매니저] 생성
        EntityManager em = emf.createEntityManager();
        // [트랜잭션] 획득
        EntityTransaction tx = em.getTransaction();
        try {
            // [트랜잭션] 시작
            tx.begin();
            // 비즈니스 로직 실행
            logic(em);
            // [트랜잭션] 커밋
            tx.commit();
        } catch (Exception e) {
            // [트랜잭션] 롤백
            tx.rollback();
        } finally {
            // [엔티티 매니저] 종료
            em.close();
        }
        // [엔티티 매니저 팩토리] 종료
        emf.close();
    }

    private static void logic(EntityManager em) {
        Long id = 1L;
        Member member = new Member();
        member.setId(id);
        member.setUsername("주니");
        member.setAge(20);
        em.persist(member);

        member.setAge(50);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember.getId() = " + findMember.getId());
        System.out.println("findMember.getUsername() = " + findMember.getUsername());
        System.out.println("findMember.getAge() = " + findMember.getAge());

        List<Member> members = em.createQuery("select m from Member2 m", Member.class).getResultList();
        System.out.println("members.size() = " + members.size());

        em.remove(member);
    }
}
