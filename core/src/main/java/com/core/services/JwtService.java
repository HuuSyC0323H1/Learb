package com.core.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 1:33 SA
 */

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    String generateChecksum(String data, String key);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String, Objects> extractClaims, UserDetails userDetails);
}
