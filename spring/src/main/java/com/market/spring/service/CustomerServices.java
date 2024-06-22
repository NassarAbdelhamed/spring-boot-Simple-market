package com.market.spring.service;

import com.market.spring.dto.responce.Profile;
import com.market.spring.models.customer.Customer;
import com.market.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    @Autowired
    private CustomerRepository customerRepository;

    public Profile getInfo(){
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer=customerRepository.findByUsername(principal.toString()).get();
        return new Profile(customer.getName(),customer.getUsername(),customer.getRole());
    }
}
