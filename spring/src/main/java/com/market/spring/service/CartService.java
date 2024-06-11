package com.market.spring.service;

import com.market.spring.dto.models.CartDto;
import com.market.spring.dto.request.SimpleCart;
import com.market.spring.models.Cart;
import com.market.spring.models.Customer;
import com.market.spring.models.Product;
import com.market.spring.repository.CartRepository;
import com.market.spring.repository.CustomerRepository;
import com.market.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartDto> findByCustomerId(long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        List<Cart> carts = cartRepository.findByCustomer(customer);
        return carts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Cart addCart(SimpleCart simpleCart){
        Customer customer = customerRepository.findById(simpleCart.getCustomerID())
                .orElseThrow(() -> new RuntimeException("user not found"));
        Product product = productRepository.findById(simpleCart.getProductID())
                .orElseThrow(() -> new RuntimeException("product not found"));

        Cart cart = new Cart();
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
}
