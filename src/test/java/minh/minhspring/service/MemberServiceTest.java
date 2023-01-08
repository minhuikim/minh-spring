package minh.minhspring.service;

import minh.minhspring.domain.Member;
import minh.minhspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// class 에서 ctrl + shift + T 누르면 자동완성
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // Dependency Injection
    // Service 에 memberRepository 입력하여 동일한 repository 사용하도록 함
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 주어진상황
        Member member = new Member();
        member.setName("hello");

        //when 실행
        Long saveId = memberService.join(member);

        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // IllegalStateException : 예외 발생 -> 성공
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // NullPointerException : 테스트 실패
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try-catch 사용
        try {
            memberService.join(member2);
            fail("fail");
        } catch (IllegalStateException e) { // 예외 : 성공(정상실행)
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123"); //(실패, 필요 : "이미 존재하는 회원입니다.123123, 실제 : "이미 존재하는 회원입니다.")
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}