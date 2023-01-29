package minh.minhspring;

import minh.minhspring.repository.MemberRepository;
import minh.minhspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
// 이후에 저장방식이 변경되면 Memory만 DB로 변경해서 바로 사용할 수 있다.
@Configuration
public class SpringConfig {

    // 스프링 데이터 JPA 구현체 등록
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // componentscan 사용하지 않고 service 에 메소드를 등록하여 사용
//    @Bean
//    public TimeTraceAop TimeTraceAop() {
//        return new TimeTraceAop();
//    }

}
