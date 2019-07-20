package spring.boot.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("loginController")
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/")
    public String linkLoginPage(){
        System.out.println("로그인");
        return "index";
    }

    @PostMapping("/")
    public String attemptLogin(){
        return "index";
    }

}
