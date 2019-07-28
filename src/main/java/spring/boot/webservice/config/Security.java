package spring.boot.webservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import spring.boot.webservice.domain.account.AuthProvider;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/user", "/user/**").permitAll()
                .antMatchers("/**").access("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/")
                .usernameParameter("userId")
                .passwordParameter("userPwd")
                .and()
                .authenticationProvider(authProvider)
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncoder = "sha256";
        Map encoders = new HashMap<>();
        encoders.put("sha256", new StandardPasswordEncoder());
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncoder, encoders);
        return passwordEncoder;
    }


}

