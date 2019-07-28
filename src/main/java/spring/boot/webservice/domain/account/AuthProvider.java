package spring.boot.webservice.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = passwordEncoder.encode(authentication.getCredentials().toString());

        Optional<Account> account = accountService.authAccount(username, password);

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        // 로그인 계정이 존재하면 권한 부여
        if (account.isPresent()) grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        // 로그인 성공시 로그인 사용자 정보 반환
        // login(spring security) >> AuthProvider.authenticate >> return ?????? >> login(Spring security) provider 완료 후 작업은????
        return new MyAuthenticaion(username, password, grantedAuthorityList, account);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

