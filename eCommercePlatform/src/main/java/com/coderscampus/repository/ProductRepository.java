package com.coderscampus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.domain.Product;
import com.coderscampus.domain.User;

public interface ProductRepository extends JpaRepository<Product, Long>
{
  public List<Product> findByUser(User user);
}
