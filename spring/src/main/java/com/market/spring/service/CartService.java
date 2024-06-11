package com.market.spring.service;

import com.market.spring.dto.models.CartDto;
import com.market.spring.dto.request.SimpleCart;
import com.market.spring.models.Cart;
import com.market.spring.models.customer.Customer;
import com.market.spring.models.Product;
import com.market.spring.repository.CartRepository;
import com.market.spring.repository.CustomerRepository;
import com.market.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;



    public Cart addCart(SimpleCart simpleCart){
        Cart cart=new Cart();
        Customer customer= customerRepository.findById(simpleCart.getCoustomerID()).get();
        Product product=productRepository.findById(simpleCart.getProductID()).get();
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQuantity(simpleCart.getQuantity());
        return cartRepository.save(cart);
    }

    private CartDto convertToDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getCustomer().getId(),
                cart.getProduct().getId(),
                cart.getQuantity()
        );
    }

    public List<Cart> findAll(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return cartRepository.findByCustomer(customer);
        } else {
            return new ArrayList<>();
        }
    }
}
