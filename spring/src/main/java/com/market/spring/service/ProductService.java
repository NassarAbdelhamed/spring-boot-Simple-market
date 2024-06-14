package com.market.spring.service;

import com.market.spring.dto.request.ProductUpgrade;
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

    public Product upgrade(long id, ProductUpgrade productUpgrade){
        Product product=productRepository.findById(id).get();
        product.setName(productUpgrade.getName());
        product.setPrice(productUpgrade.getPrice());
        product.setDescription(productUpgrade.getDescription());
        product.setValid(productUpgrade.isValid());
        return productRepository.save(product);
    }

    public Product changeValid(long id){
        Product product=productRepository.findById(id).get();
        product.setValid(!product.isValid());
        return productRepository.save(product);
    }

    public List<Product> allProduct() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return productRepository.findAll();
    }
}
