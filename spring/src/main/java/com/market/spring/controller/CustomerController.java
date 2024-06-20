package com.market.spring.controller;


import com.market.spring.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerServices customerServices;
    @GetMapping
    public ResponseEntity<?> profile(){
        return new ResponseEntity<>(customerServices.getInfo(), HttpStatus.OK);
    }
}
