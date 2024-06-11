package com.market.spring.authentication;

import lombok.Getter;

@Getter
public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
