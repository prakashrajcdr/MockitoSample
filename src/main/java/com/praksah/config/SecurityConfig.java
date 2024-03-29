package com.praksah.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .anyRequest().authenticated()
               .and().oauth2Login();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
//    	web.ignoring();
    	web.ignoring()
    	.antMatchers("/api/getAllCustomers")
    	.antMatchers("/api/addCustomer")
    	.antMatchers("/api/getCustomer/{id}")
    	.antMatchers("/api/updateCustomer/{id}")
    	.antMatchers("/api/removeCustomer/{id}");
    }
}