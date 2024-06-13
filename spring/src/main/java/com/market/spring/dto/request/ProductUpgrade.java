package com.market.spring.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpgrade {

    private String name;
    private double price;
    private String description;
    private  boolean isValid;

}
