package com.core.services;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 2:04 SA
 */

public interface UserService {
    UserDetailsService userDetailsService();
}
