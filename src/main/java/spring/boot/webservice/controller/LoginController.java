package spring.boot.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.boot.webservice.domain.account.Account;
import spring.boot.webservice.domain.account.AccountService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    AccountService accountService = new AccountService();

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String loginPage() {
        return "intro";
    }

    // 로그인 검증
    @RequestMapping(value = "/user/auth", method = RequestMethod.POST)
    public String chkAccount(HttpServletRequest req) {
        accountService.loadUserByUsername(req.getParameter("userId"));
        return "main";
    }
}
