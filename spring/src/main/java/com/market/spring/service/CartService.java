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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        if(product.isValid()) {
            cart.setCustomer(customer);
            cart.setProduct(product);
            cart.setQuantity(addCartRequest.getQuantity());
            cart.setDate(new Date(System.currentTimeMillis()));
            return cartRepository.save(cart);
        }
        else {
            throw new RuntimeException();
        }
    }

    public CartResponce findAll() {
       long id = CurrentID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Customer customer = customerRepository.findById(id).get();
        List<Cart> cartList= cartRepository.findByCustomer(customer);
        CartResponce cartResponce =new CartResponce();
        cartResponce.setName(customer.getName());
        List<ProductList> list=new ArrayList<>();
        for ( Cart i : cartList){
            list.add(new ProductList(sdf.format(i.getDate()),i.getProduct().getName(),i.getQuantity()));
        }
        cartResponce.setProudcts(list);
        return cartResponce;
    }
}
