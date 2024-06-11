package com.market.spring.repository;

import com.market.spring.models.Cart;
import com.market.spring.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByCustomer(Customer customer);
}
