package com.coderscampus.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.domain.User;
import com.coderscampus.repository.UserRepository;
import com.coderscampus.security.Authority;

@Service
public class UserService
{
  private UserRepository userRepo;
  
  public User saveUser(User user)
  {
    // assign default spring security roles
    
    Set<Authority> authorities = new HashSet<Authority>();
    
    authorities.add(new Authority("ROLE_USER", user));
    authorities.add(new Authority("ROLE_DASHBOARD", user));
    
    user.setAuthorities(authorities);
    
    return userRepo.save(user);
  }

  @Autowired
  public void setUserRepo(UserRepository userRepo)
  {
    this.userRepo = userRepo;
  }
}
