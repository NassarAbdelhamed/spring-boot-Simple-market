package com.market.spring.config;

import com.market.spring.service.AuthenticationService;
import com.market.spring.models.customer.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    public String generateToken(Customer customer, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(customer.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key generateKey(){
        byte[] secreateAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secreateAsBytes);
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }


    // Clean tokenBlacklist every hour to make its size as small as possible
    @Scheduled(cron = "0 0 * * * ?")
    public void CleanTokenBlacklist() {
        List<String> forDelete=new ArrayList<>();
      for (String i :AuthenticationService.tokenBlacklist){
            try {
                extractAllClaims(i);
            }
            catch (Exception e){
                forDelete.add(i);
            }
      }
      for(String i :forDelete) {
          AuthenticationService.tokenBlacklist.remove(i);
      }
        forDelete.clear();
        System.out.println("Clean is done!");
    }

}
