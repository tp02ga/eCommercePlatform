package com.coderscampus.domain;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order
{
  private Long id;
  private Date date;
  private BigInteger total;
  private User user;  // many-to-one
  private Set<Product> products; // many-to-many
  
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
  public Date getDate()
  {
    return date;
  }
  public void setDate(Date date)
  {
    this.date = date;
  }
  public BigInteger getTotal()
  {
    return total;
  }
  public void setTotal(BigInteger total)
  {
    this.total = total;
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
  
  @ManyToMany
  @JoinTable(name="product_order", joinColumns=@JoinColumn(name="order_id"), inverseJoinColumns=@JoinColumn(name="product_id"))
  public Set<Product> getProducts()
  {
    return products;
  }
  public void setProducts(Set<Product> products)
  {
    this.products = products;
  }
  
  
}
