package com.core.services.impl;

import com.core.infrastructure.constant.ErrorCode;
import com.core.infrastructure.exception.NVException;
import com.core.model.User;
import com.core.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 1:34 SA
 */

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${secretKey}")
    private String secretKey;

    @Value("${key}")
    private String key;

    @Override
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateChecksum(String data, String key) {
        String checkSum = "";
        try {
            String keys = data + key;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(keys.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            checkSum = sb.toString();
        } catch (Exception e) {
           throw new NVException(e.getMessage());
        }
        log.info("Checksum: " + checkSum);
        return checkSum;
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
        byte[] key = Decoders.BASE64.decode(secretKey);
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
//        String token = Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//
//        String checksum = generateChecksum(token);
//
//        Claims claims = Jwts.claims().setSubject(token);
//        claims.put("checksum", checksum);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();