package com.market.spring.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationRequest {
    private String name;
    private String username;
    private String password;
}
