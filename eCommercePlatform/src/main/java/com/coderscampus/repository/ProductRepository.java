package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}
