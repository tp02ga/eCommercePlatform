package com.coderscampus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
  {
    auth.userDetailsService(userDetailsService);
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception 
  {
    http
    .csrf().disable()
    .authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/products/**").permitAll()
      .antMatchers("/itemsInCart").permitAll()
      .antMatchers("/css/**").permitAll()
      .antMatchers("/js/**").permitAll()
      .antMatchers("/images/**").permitAll()
      .antMatchers("/page*").permitAll()
      .antMatchers("/register").permitAll()
      .antMatchers("/dashboard").hasAnyRole("DASHBOARD")
      .anyRequest().authenticated()
      .and()
    .formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/dashboard")
      .permitAll()
      .and()
    .logout().logoutSuccessUrl("/");
  }
}
