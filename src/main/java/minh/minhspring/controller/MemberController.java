package minh.minhspring.controller;

import minh.minhspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // 스프링 컨테이너에 등록해서 사용
    private final MemberService memberService;

    // 스프링 컨테이너에 등록해서 사용
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
