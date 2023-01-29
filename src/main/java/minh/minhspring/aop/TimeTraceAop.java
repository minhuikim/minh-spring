package minh.minhspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    // AOP적용 타겟팅 (패키지 하위 전체 적용)
    @Around("execution(* minh.minhspring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
           return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

    /*
        출력 방식

        START: execution(String minh.minhspring.controller.MemberController.list(Model))
        START: execution(List minh.minhspring.service.MemberService.findMembers())
        START: execution(List org.springframework.data.repository.ListCrudRepository.findAll())
        END: execution(List org.springframework.data.repository.ListCrudRepository.findAll()) 8ms
        END: execution(List minh.minhspring.service.MemberService.findMembers()) 8ms
        END: execution(String minh.minhspring.controller.MemberController.list(Model)) 9ms
    * */
}
