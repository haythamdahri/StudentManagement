package com.student.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={"com.student.manager"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Register some users to grant them the access to the whole application
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("haytham").password("toortoor").roles(new String[]{"ADMIN", "SUPER ADMIN", "MANAGER"}))
                .withUser(users.username("imrane").password("man").roles(new String[]{"AMDIN", "MANAGER"}));
    }

    // Allow all users including unauthenticated to use static files
    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/less/**", "/metadata/**", "/scss/**", "/sprites/**", "/svgs/**", "/webfonts/**");
    }

    // Secure the whole application using login page for registred users
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();



        http.authorizeRequests()
                .antMatchers("/**").hasAnyRole("ADMIN", "SUPER ADMIN", "MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
