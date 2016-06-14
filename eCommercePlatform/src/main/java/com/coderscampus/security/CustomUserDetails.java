package com.coderscampus.security;

import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.coderscampus.domain.User;

public class CustomUserDetails extends User implements UserDetails
{
  private static final long serialVersionUID = 344094893169692400L;

  public CustomUserDetails (User user)
  {
    BeanUtils.copyProperties(user, this);
  }
  
  @Override
  public Set<Authority> getAuthorities()
  {
    return super.getAuthorities();
  }

  @Override
  public String getPassword()
  {
    return super.getPassword();
  }

  @Override
  public String getUsername()
  {
    return super.getEmail();
  }

  @Override
  public boolean isAccountNonExpired()
  {
    return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
    return true;
  }

  @Override
  public boolean isEnabled()
  {
    return true;
  }

}
