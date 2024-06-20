package com.market.spring.service;

import com.market.spring.config.JwtService;
import com.market.spring.dto.request.AuthenticationRequest;
import com.market.spring.dto.responce.AuthenticationResponse;
import com.market.spring.models.customer.Customer;
import com.market.spring.models.customer.Role;
import com.market.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtService jwtService;


    private final PasswordEncoder passwordEncoder;


  // Set of all logout JWT
   public static Set<String> tokenBlacklist = new HashSet<>();

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationResponse register(AuthenticationRequest request){
        var customer = new Customer();
        customer.setName(request.getName());
        customer.setUsername(request.getUsername());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRole(Role.CUSTOMER);
        customerRepository.save(customer);
        String token = jwtService.generateToken(customer, generateExtraClaims(customer));
        return  new AuthenticationResponse(token);
    }




    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authToken);
        Customer customer = customerRepository.findByUsername(authenticationRequest.getUsername()).get();
        String jwt = jwtService.generateToken(customer, generateExtraClaims(customer));
        return new AuthenticationResponse(jwt);
    }

    public void logout(String token) {
        tokenBlacklist.add(token.split(" ")[1]);
    }


    private Map<String, Object> generateExtraClaims(Customer customer) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", customer.getName());
        extraClaims.put("role", customer.getRole().name());
        return extraClaims;
    }

    public  static boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }

}