package ru.zagorodnikova.tm.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/")
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user-signUp").permitAll()
                .antMatchers("/user-signIn").permitAll()
                .and()
                .formLogin()
                .loginPage("/user-signIn")
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/user-signIn")
                .and()
                .csrf().disable();
    }
}
