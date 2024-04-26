package com.core.services.impl;

import com.core.model.User;
import com.core.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 1:34 SA
 */

@Service
public class JwtServiceImpl implements JwtService {


    @Override
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getAudience);
    }

    private<T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaim(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaim(String token) {

        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJwt(token).getBody();
    }

    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode("4234F324352409S342349293A5435FH453423423E3453454W465721");
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }

    @Override
    public String generateRefreshToken(Map<String, Objects> extractClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
