package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    //RequestParam 피라미터값  문자열 "name" 을 모델에 담는다.
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // name에 길동 넣으면 -> hello 길동
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name); //빋은 name을
        return hello; //객체를 넘겨준다.
    }

    //class안에 class 또 사용 가능하다. HelloController.Hello
    static class Hello {
        private String name; //private기때문에 게터세터에 의해(자바빈표준규ㅇ약)

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
