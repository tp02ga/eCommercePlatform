package com.coderscampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderscampus.domain.User;
import com.coderscampus.repository.UserRepository;
import com.coderscampus.security.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
  @Autowired
  private UserRepository userRepo;
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
  {
    User user = userRepo.findByEmail(email);
    
    if (user == null)
      throw new UsernameNotFoundException("User with email: " + email + " was not found");
    
    return new CustomUserDetails(user);
  }

}
