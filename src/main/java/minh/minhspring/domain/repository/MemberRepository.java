package minh.minhspring.domain.repository;

import minh.minhspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);     // Optional 반환값이 null인 경우 Optional을 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
