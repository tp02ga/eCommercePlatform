package com.coderscampus.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product
{
  private Long id;
  private Byte[] image;
  private String imageUrl;
  private Double price; 
  private String description;
  private String shortDescription;
  private User user;
  private Set<Review> reviews = new HashSet<>();
  private Set<Cart> carts = new HashSet<>();
  
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

  public Byte[] getImage()
  {
    return image;
  }

  public void setImage(Byte[] image)
  {
    this.image = image;
  }

  public Double getPrice()
  {
    return price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  @Column(length=2000)
  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getShortDescription()
  {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription)
  {
    this.shortDescription = shortDescription;
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

  @OneToMany(cascade=CascadeType.ALL, mappedBy="product", fetch=FetchType.EAGER)
  public Set<Review> getReviews()
  {
    return reviews;
  }

  public void setReviews(Set<Review> reviews)
  {
    this.reviews = reviews;
  }

  @ManyToMany(cascade=CascadeType.ALL, mappedBy="products", fetch=FetchType.EAGER)
  public Set<Cart> getCarts()
  {
    return carts;
  }

  public void setCarts(Set<Cart> carts)
  {
    this.carts = carts;
  }

  public String getImageUrl()
  {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl)
  {
    this.imageUrl = imageUrl;
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
    Product other = (Product) obj;
    if (id == null)
    {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
