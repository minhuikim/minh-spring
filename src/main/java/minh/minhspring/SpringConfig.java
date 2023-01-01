package minh.minhspring;

import minh.minhspring.domain.repository.MemberRepository;
import minh.minhspring.domain.repository.MemoryMemberRepository;
import minh.minhspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
// 이후에 저장방식이 변경되면 Memory만 DB로 변경해서 바로 사용할 수 있다.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
