package spring.boot.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import spring.boot.webservice.domain.account.Account;
import spring.boot.webservice.domain.account.AccountService;

@Component
public class AccountRunner implements ApplicationRunner{

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account wonjun = accountService.creatAccount("wonjun", "wonjun");
        System.out.println("wonjun " + wonjun.getUsername() + " " + wonjun.getPassword());
    }
}
