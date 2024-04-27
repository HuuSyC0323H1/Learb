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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Value(value = "${regex}")
    private String PASSWORD_PATTERN;

    public User signup(RequestSignUp requestSignUp){
        if (!isValid(requestSignUp.getPassword())){
            throw new NVException(ErrorCode.PASSWORD_INVALID_FORMAT);
        }
        if (requestSignUp.getPassword().equals(requestSignUp.getConfirmPassword())){
            throw new NVException(ErrorCode.DUPLICATE_PASSWORD);
        }
        User userNew = new User();
        Optional<User> user = userRepository.findByUserName(requestSignUp.getUserName());
        if (user.isPresent()) throw new NVException(ErrorCode.USER_REGISTERED);
        userNew.setFullName(requestSignUp.getFullName());
        userNew.setUserName(requestSignUp.getUserName());
        userNew.setPassword(passwordEncoder.encode(requestSignUp.getPassword()));
        userNew.setRole(Role.USER);
        userRepository.save(userNew);
        return userNew;
    }

    @Override
    public AuthenticationResponse signIn(RequestSignIn requestSignIn){
        if (!isValid(requestSignIn.getPassword())){
            throw new NVException(ErrorCode.PASSWORD_INVALID_FORMAT);
        }
        try {
            authen.authenticate(new UsernamePasswordAuthenticationToken(
                    requestSignIn.getUserName(), requestSignIn.getPassword()));
        } catch (AuthenticationException e) {
            throw new NVException(ErrorCode.USERNAME_OR_PASSWORD_INVALID);
        }
        User user = userRepository.getUserByUserName(requestSignIn.getUserName());
        String jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        authenticationResponse.setUser(userResponse);
        authenticationResponse.setToken(jwt);
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
            return authenticationResponse;
        }
        return null;
    }

    private boolean isValid(final String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
