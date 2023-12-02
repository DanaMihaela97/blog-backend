package com.example.blog.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    private static final String SECRET_KEY="2F423F4528482B4D6250655368566D597133743677397A24432646294A404E63";
    private final List<String> blacklist = new ArrayList<>();
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject); //  subject = the email/username
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // username in spring is EMAIL
               // .claim("role", userDetails.getAuthorities().toArray()[0].toString())
                .setIssuedAt(new Date(System.currentTimeMillis())) // check if the token is still valid or not
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10 )) //600 000 - 10 minutes
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){ // if token belongs to userDetails
        final String username = extractUsername(token);
        System.out.println((username.equals(userDetails.getUsername())) && !isTokenExpired(token));
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && !blacklist.contains(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public void invalidateToken(String token){
        blacklist.add(token);
    }
}
