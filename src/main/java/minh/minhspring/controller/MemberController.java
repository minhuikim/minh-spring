package minh.minhspring.controller;

import minh.minhspring.domain.Member;
import minh.minhspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

   // 스프링 컨테이너에 등록해서 사용
    private final MemberService memberService;

    // 생성자를 통해 스프링 컨테이너에 등록해서 사용
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // MemberController 호출 없이 컨테이너 등록하는 방식
    // 필드 직접 주입 (권장하지 않는 방식)
    // @Autowired private MemberService memberService;

    // setter 주입 (setter injection) - Service를 public으로 노출해야하는 단점이 발생
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        //member = spring!

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
