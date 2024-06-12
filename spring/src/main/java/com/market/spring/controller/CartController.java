package com.market.spring.controller;

import com.market.spring.dto.request.AddCartRequest;
import com.market.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {



    @Autowired
    CartService cartService;



    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody AddCartRequest simpleCart){

        return new ResponseEntity<>(cartService.addCart(simpleCart),HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(cartService.findAll(),HttpStatus.OK);
    }
}
