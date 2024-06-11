package com.market.spring.controller;


import com.market.spring.config.JwtAuthenticationFilter;
import com.market.spring.models.Product;
import com.market.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<?> allProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(
            productService.allProduct()
        );
    }


    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody  Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productService.addProduct(product)
        );
    }
}
