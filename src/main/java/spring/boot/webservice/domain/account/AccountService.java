package spring.boot.webservice.domain.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.webservice.util.Uuid;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService{

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account creatAccount(String username, String password) {

        logger.info(">>> CREATE ACCOUNT username : " + username);

        Uuid uuid = new Uuid();
        Account account = new Account();
        account.setUid(uuid.getUuid());
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }

    public Optional<Account> authAccount(String username, String password){

        logger.info(">>> AUTH ACCOUNT username : " + username);
        logger.info(">>> AUTH ACCOUNT username : " + passwordEncoder.encode(password));

        Optional<Account> account = accountRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password));

        return account;

    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
        User user = new User(account.getUsername(), account.getPassword(), authorities());
        System.out.println(user.getUsername() + " " + user.getPassword());
        return user;
    }

}
