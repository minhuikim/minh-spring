package minh.minhspring.service;

import minh.minhspring.domain.Member;
import minh.minhspring.repository.MemberRepository;
import minh.minhspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // Service 에서 repository 직접 사용하지 않고 외부에서 해당 repository 입력하여 사용
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //  Optional 을 사용하지 않고 바로 코드작성 가능
//        memberRepository.findByName(member.getName())
//            .ifPresent(m -> {
//                throw new IllegalStateException("이미 존재하는 회원입니다.");
//            });

        //  메소드로 작성하여 사용
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
