package com.jpa.javaormstandardjpa.chapter05;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {
    public static void main(String[] args) {
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
        Team team1 = new Team("team1", "팀1");
        Member2 member1 = new Member2("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member2 member2 = new Member2("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        Team team2 = new Team("team2", "팀2");
        member1.setTeam(team2);
        List<Member2> members = team1.getMembers();
        for (var e : members) {
            System.out.println("e.toString() = " + e.toString());
        }
        System.out.println("team2.getMembers() = " + team2.getMembers());
//        System.out.println("members.get(0).getTeam() = " + members.get(0).getTeam());


//
//        Member2 findMember = em.find(Member2.class, "member1");
//        Team findTeam = findMember.getTeam();
//        System.out.println("findTeam = " + findTeam.getName());
//
//        String jpql = "SELECT m FROM Member2 m JOIN m.team t WHERE t.name = :teamName";
//
//        List<Member2> resultList = em.createQuery(jpql, Member2.class)
//                .setParameter("teamName", "팀1")
//                .getResultList();
//        for (var e : resultList) {
//            System.out.println("e.toString() = " + e.toString());
//        }
//
//        Team team2 = new Team("team2", "팀2");
//        em.persist(team2);
//
//        Member2 member = em.find(Member2.class, "member1");
//        member.setTeam(team2);
//
//        Team team = em.find(Team.class, "team1");
//        List<Member2> members = team.getMembers();
//        for (var e : members) {
//            System.out.println("members = " + e.toString());
//        }
    }
}
