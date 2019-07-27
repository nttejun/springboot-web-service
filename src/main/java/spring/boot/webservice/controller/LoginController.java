package spring.boot.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.boot.webservice.domain.account.AccountService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    AccountService accountService = new AccountService();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "join";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String creatAccount(HttpServletRequest req){
        System.out.println(" >>>> " + req.getParameter("joinId"));
        accountService.creatAccount(req.getParameter("joinId"), req.getParameter("joinPwd"));
        return "login";
    }

}
