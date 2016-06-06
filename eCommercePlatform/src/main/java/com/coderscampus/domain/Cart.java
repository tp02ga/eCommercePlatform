package com.coderscampus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Cart
{
  private Long userId;
  private Integer quantity;
  private Date dateAdded;
  private User user;
  private Set<Product> products = new HashSet<>();
  
  @Id  
  @GeneratedValue(generator="myGenerator")  
  @GenericGenerator(name="myGenerator", strategy="foreign", parameters=@Parameter(value="user", name = "property"))
  public Long getUserId()
  {
    return userId;
  }

  public void setUserId(Long userId)
  {
    this.userId = userId;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public void setQuantity(Integer quantity)
  {
    this.quantity = quantity;
  }

  public Date getDateAdded()
  {
    return dateAdded;
  }

  public void setDateAdded(Date dateAdded)
  {
    this.dateAdded = dateAdded;
  }

  @OneToOne
  @JoinColumn(name="user_id")
  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  @ManyToMany
  @JoinTable(name="product_cart", joinColumns=@JoinColumn(name="cart_id"), inverseJoinColumns=@JoinColumn(name="product_id"))
  public Set<Product> getProducts()
  {
    return products;
  }

  public void setProducts(Set<Product> products)
  {
    this.products = products;
  }
}
