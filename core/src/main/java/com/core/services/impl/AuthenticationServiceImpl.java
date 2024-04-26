package com.core.services.impl;

import com.core.api.forms.RequestSignIn;
import com.core.api.forms.RequestSignUp;
import com.core.api.view.AuthenticationResponse;
import com.core.api.view.UserResponse;
import com.core.dto.RefreshTokenRequest;
import com.core.infrastructure.constant.ErrorCode;
import com.core.infrastructure.exception.NVException;
import com.core.model.User;
import com.core.model.enums.Role;
import com.core.repositories.UserRepository;
import com.core.services.AuthenticationService;
import com.core.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 3:07 SA
 */

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authen;

    @Autowired
    private JwtService jwtService;

    private final ModelMapper modelMapper = new ModelMapper();

    public User signup(RequestSignUp requestSignUp){
        User userNew = new User();
        Optional<User> user = userRepository.findByUserName(requestSignUp.getUserName());
        if (!user.isEmpty()) throw new NVException(ErrorCode.USER_REGISTERED);
        userNew.setFullName(requestSignUp.getFullName());
        userNew.setUserName(requestSignUp.getUserName());
        userNew.setPassword(passwordEncoder.encode(requestSignUp.getPassword()));
        userNew.setRole(Role.USER);
        userRepository.save(userNew);
        return userNew;
    }

    @Override
    public AuthenticationResponse signIn(RequestSignIn requestSignIn){
        authen.authenticate(new UsernamePasswordAuthenticationToken(
                requestSignIn.getUserName(), requestSignIn.getPassword()));
        User user = userRepository.getUserByUserName(requestSignIn.getUserName());
        if (user == null) throw new NVException(ErrorCode.USERNAME_OR_PASSWORD_INVALID);
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        authenticationResponse.setUser(userResponse);
        authenticationResponse.setToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);
        return authenticationResponse;
    }

    @Override
    public AuthenticationResponse responseToken(RefreshTokenRequest refreshTokenRequest){
        String userName = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.getUserByUserName(userName);

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            String jwt = jwtService.generateToken(user);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            authenticationResponse.setUser(userResponse);
            authenticationResponse.setToken(jwt);
            authenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return authenticationResponse;
        }
        return null;
    }
}
