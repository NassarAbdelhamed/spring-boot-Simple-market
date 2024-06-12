package com.market.spring.service;


import com.market.spring.dto.request.AddCartRequest;
import com.market.spring.dto.responce.CartResponce;
import com.market.spring.dto.responce.ProductList;
import com.market.spring.models.Cart;
import com.market.spring.models.customer.Customer;
import com.market.spring.models.Product;
import com.market.spring.repository.CartRepository;
import com.market.spring.repository.CustomerRepository;
import com.market.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

   private long CurrentID(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Object principal=authentication.getPrincipal();
       Customer customer=customerRepository.findByUsername(principal.toString()).get();
       return customer.getId();
   }

    public Cart addCart(AddCartRequest addCartRequest){
        long id = CurrentID();
        Cart cart=new Cart();
        Customer customer= customerRepository.findById(id).get();
        Product product=productRepository.findById(addCartRequest.getProductID()).get();
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQuantity(addCartRequest.getQuantity());
        return cartRepository.save(cart);
    }

//    private CartDto convertToDto(Cart cart) {
//        return new CartDto(
//                cart.getId(),
//                cart.getCustomer().getId(),
//                cart.getProduct().getId(),
//                cart.getQuantity()
//        );
//    }

    public CartResponce findAll() {
       long id = CurrentID();
            Customer customer = customerRepository.findById(id).get();
            List<Cart> cartList= cartRepository.findByCustomer(customer);
            CartResponce cartResponce =new CartResponce();
            cartResponce.setName(customer.getName());
            List<ProductList> list=new ArrayList<>();
            for ( Cart i : cartList){
                list.add(new ProductList(i.getId(),i.getProduct().getName(),i.getQuantity()));
            }
            cartResponce.setProudcts(list);
            return cartResponce;
    }
}
