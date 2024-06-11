package com.market.spring.controller;

import com.market.spring.dto.request.SimpleCart;
import com.market.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {



    @Autowired
    CartService cartService;



    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody SimpleCart simpleCart){

        return new ResponseEntity<>(cartService.addCart(simpleCart),HttpStatus.CREATED);
    }

    @GetMapping("/{coustomerID}")
    public ResponseEntity<?> findAll(@PathVariable long coustomerID){
        return new ResponseEntity<>(cartService.findAll(coustomerID),HttpStatus.OK);
    }
}
