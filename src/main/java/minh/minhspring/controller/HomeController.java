package minh.minhspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}

/*
* 정적 컨텐츠 우선순위
*
* 1. 스프링 컨테이너 내 관련 컨트롤러 조회
* 2. 없으면 static 파일 조회
* */