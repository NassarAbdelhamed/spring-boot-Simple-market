package com.market.spring.controller;

import com.market.spring.dto.models.CartDto;
import com.market.spring.dto.request.SimpleCart;
import com.market.spring.models.Cart;
import com.market.spring.models.Customer;
import com.market.spring.models.Product;
import com.market.spring.service.CartService;
import com.market.spring.service.CustomerService;
import com.market.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @PostMapping("/add/customer")
    public Customer addCustomer(@RequestBody Customer customer){
      return customerService.addCustomer(customer);
    }

    @PostMapping("/add/product")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PostMapping("/add/cart")
    public Cart addProduct(@RequestBody SimpleCart simpleCart){
        return cartService.addCart(simpleCart);
    }

    @GetMapping("/cart/{customerId}")
    public List<CartDto> findall(@PathVariable long customerId){
        return cartService.findByCustomerId(customerId);
    }
}
