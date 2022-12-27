package minh.minhspring.service;

import minh.minhspring.domain.Member;
import minh.minhspring.repository.MemberRepository;
import minh.minhspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service    // 스프링이 올라올 때 서비스로 등록
@Component  // @Service에 포함되어있음
public class MemberService {

    private final MemberRepository memberRepository;

    // Service 에서 repository 직접 사용하지 않고 외부에서 해당 repository 입력하여 사용
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // Autowired
    // 스프링 생성 시 MemberService 는 MemberRepository 가 필요한데,
    // Autowired 가 되어있으면 MemberService 를 스프링이 생성할 때
    // @Service를 스프링 컨테이너에 등록하고, 생성자를 호출한다.
    // 호출 시 Autowired가 있으면 MemberRepository를 MemberService에 자동으로 주입해준다.

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
