package com.market.spring.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleCart {
    long coustomerID;
    long productID;
    int Quantity;

}
