package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.coderscampus.domain.Cart;
import com.coderscampus.domain.User;

public interface CartRepository extends JpaRepository<Cart, Long>
{

//  @Query("select c Cart c join c.user where c.user = :user")
  Cart findByUser(@Param("user") User user);

}
