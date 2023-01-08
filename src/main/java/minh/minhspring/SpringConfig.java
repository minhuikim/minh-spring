package minh.minhspring;

import minh.minhspring.repository.*;
import minh.minhspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import javax.sql.DataSource;

// 자바 코드로 직접 스프링 빈 등록하기
// 이후에 저장방식이 변경되면 Memory만 DB로 변경해서 바로 사용할 수 있다.
@Configuration
public class SpringConfig {

    // 스프링에서 자동으로 db 연결해줌
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new jdbcMemberRepository(dataSource);          // 현재는 실무에서 거의 사용되지 않는 방식
//        return new jdbcTemplateMemberRepository(dataSource);    // 현재도 실무에서 많이 사용되는 방식
        return new jpaMemberRepository(em);
    }
}
