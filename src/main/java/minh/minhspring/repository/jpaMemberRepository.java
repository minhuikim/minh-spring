package minh.minhspring.repository;

import minh.minhspring.domain.Member;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class jpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    // 생성자
    public jpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member); // persist 영구저장하다
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // find 조회
        return Optional.ofNullable(member); // 값이 없을수도 있음
    }

    public List<Member> findAll() {
        // 객체(Member, Entity)를 상대로 쿼리 전달
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }
}