package minh.minhspring.repository;

import minh.minhspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA
// 다중상속
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name); // JpaRepository 에서 인터페이스를 통한 기본적인 CRUD 제공
}
