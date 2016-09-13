package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>
{

}
