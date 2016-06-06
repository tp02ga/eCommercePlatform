package com.coderscampus.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User
{
  private Long id;
  private String role;
  private String password;
  private String email;
  private Set<Product> products = new HashSet<>();
  private Set<Review> reviews = new HashSet<>();
  private Cart cart;
  
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

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  @OneToMany(cascade=CascadeType.ALL, mappedBy="user")
  public Set<Product> getProducts()
  {
    return products;
  }

  public void setProducts(Set<Product> products)
  {
    this.products = products;
  }

  @OneToMany(cascade=CascadeType.ALL, mappedBy="user")
  public Set<Review> getReviews()
  {
    return reviews;
  }

  public void setReviews(Set<Review> reviews)
  {
    this.reviews = reviews;
  }

  @OneToOne(cascade=CascadeType.ALL, mappedBy="user")
  public Cart getCart()
  {
    return cart;
  }

  public void setCart(Cart cart)
  {
    this.cart = cart;
  }
}
