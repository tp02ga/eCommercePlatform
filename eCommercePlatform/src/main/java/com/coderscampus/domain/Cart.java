package com.coderscampus.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cart
{
  private Long id;
  private Integer quantity;
  private Date dateAdded;
  private User user;
  private Set<Product> products = new HashSet<>();
  
  public Cart () {
    
  }
  
  @Id  
  @Column(name="user_id")
  @GeneratedValue(generator="myGenerator")  
  @GenericGenerator(name="myGenerator", strategy="foreign", parameters=@Parameter(value="user", name = "property"))
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
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

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Cart other = (Cart) obj;
    if (id == null)
    {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  
}
