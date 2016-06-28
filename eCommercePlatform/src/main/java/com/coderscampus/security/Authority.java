package com.coderscampus.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import com.coderscampus.domain.User;

@Entity
public class Authority implements GrantedAuthority
{
  private static final long serialVersionUID = 8076714901577427445L;
  private Long id;
  private User user;
  private String role;

  public Authority()
  {
  }
  
  public Authority (String role, User user)
  {
    this.role = role;
    this.user = user;
  }
  
  @Id
  @GeneratedValue
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  @Override
  public String getAuthority()
  {
    return role;
  }

  @ManyToOne
  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public void setAuthority(String role)
  {
    this.role = role;
  }

}
