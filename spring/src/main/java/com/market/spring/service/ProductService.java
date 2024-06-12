package com.market.spring.service;

import com.market.spring.models.Product;
import com.market.spring.models.customer.Customer;
import com.market.spring.repository.CustomerRepository;
import com.market.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> allProduct() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//            Customer customer=customerRepository.findByUsername(principal.toString()).get();
//            System.out.println(customer.getId()+ " : "+customer.getName());
//        }
        return productRepository.findAll();
    }
}
