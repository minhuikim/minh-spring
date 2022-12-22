package minh.minhspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloContoller {

    @GetMapping("hello")
    public String hello(Model model) {
        // 변수 data에 값 hello!! 입력
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        // http://localhost:8080/hello-mvc?name=spring!
        // hello spring!
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http body에 데이터를 직접 넣어준다. (view 가 없음, 소스보기 해도 hello spring 만 출력)
    public String helloString(@RequestParam("name") String name) {
        // http://localhost:8080/hello-string?name=spring!
        // 연결된 html 페이지 없어도 "hello spring!"출력 가능
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody   // 기본적으로 json으로 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체 생성
        hello.setName(name);
        // 객체를 리턴 -> JSON 방식 출력
        // {"name":"spring!"} 키 : name, 값 : spring!
        return hello;
    }

    static class Hello {
        private String name;

        public String getNAme() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

