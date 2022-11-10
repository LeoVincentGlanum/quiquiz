
package com.example.demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                // URL autorizations:
                .authorizeRequests()
                // The order of the rules matters and the more specific rules should go first.
                // ne pas oublier le / devant les URLs
                .antMatchers("/", "/index.html","/api/login").permitAll()
                .anyRequest().authenticated()

                // Authentication mode:
                .and().formLogin()
                .and().httpBasic()
                .and().csrf().disable();
    }
}