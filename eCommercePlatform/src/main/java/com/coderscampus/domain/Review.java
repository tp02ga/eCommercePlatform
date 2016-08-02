package com.coderscampus.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review
{
  private Long id;
  private String text;
  private Integer rating;
  private Date date;
  private User user;
  private Product product;
  
  
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

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public Integer getRating()
  {
    return rating;
  }

  public void setRating(Integer rating)
  {
    this.rating = rating;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  @ManyToOne(fetch=FetchType.EAGER)
  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  @ManyToOne
  public Product getProduct()
  {
    return product;
  }

  public void setProduct(Product product)
  {
    this.product = product;
  }
  
}
