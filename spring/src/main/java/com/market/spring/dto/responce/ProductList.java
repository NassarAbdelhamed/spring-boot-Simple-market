package com.market.spring.dto.responce;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductList {
    long CartId;
    String ProductName;
    int quantity;
}
