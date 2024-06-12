package com.market.spring.authentication;

import com.market.spring.config.JwtService;
import com.market.spring.models.customer.Customer;
import com.market.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private CustomerRepository userRepository;

   @Autowired
   private JwtService jwtService;


   private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationResponse register(Customer request){
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setUsername(request.getUsername());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRole(request.getRole());
        userRepository.save(customer);
        saveContext(customer.getUsername(),request.getPassword());
        String token = jwtService.generateToken(customer, generateExtraClaims(customer));
        return  new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        saveContext(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        Customer customer= userRepository.findByUsername(authenticationRequest.getUsername()).get();
        String jwt = jwtService.generateToken(customer, generateExtraClaims(customer));
        return new AuthenticationResponse(jwt);
    }

    private  void saveContext(String username,String password){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, password
        );
        Authentication authentication =authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private Map<String, Object> generateExtraClaims(Customer customer) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", customer.getName());
        extraClaims.put("role", customer.getRole().name());
        return extraClaims;
    }
}
