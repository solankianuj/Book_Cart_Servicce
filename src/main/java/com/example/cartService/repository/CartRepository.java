package com.example.cartService.repository;

import com.example.cartService.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Long> {
}
