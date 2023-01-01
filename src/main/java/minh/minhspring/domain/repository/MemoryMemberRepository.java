package minh.minhspring.domain.repository;

import minh.minhspring.domain.Member;
import org.jetbrains.annotations.NotNull;

import java.util.*;

// Controller -> Service -> Repository(저장)
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // <id type, Member>
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(@NotNull Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 모든 테스트는 순서대로 실행되지 않기 때문에 테스트 하나가 완료된 후 clear해줘야한다.
    public void clearStore() {
        store.clear();
    }
}
