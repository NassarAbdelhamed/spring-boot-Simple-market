package com.market.spring.dto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartDto {
    private long id;
    private long customerId;
    private long productId;
    private int quantity;
}