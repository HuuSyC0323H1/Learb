package com.core.api.controller.user;

import com.core.api.forms.RequestSignIn;
import com.core.api.forms.RequestSignUp;
import com.core.api.forms.ResponseObject;
import com.core.dto.RefreshTokenRequest;
import com.core.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 3:05 SA
 */

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseObject signUp(@RequestBody RequestSignUp requestSignUp) {
        return new ResponseObject(authenticationService.signup(requestSignUp));
    }

    @PostMapping("/signing")
    public ResponseObject signIn(@RequestBody RequestSignIn requestSignIn) {
        return new ResponseObject(authenticationService.signIn(requestSignIn));
    }

    @PostMapping("/refresh")
    public ResponseObject refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ResponseObject(authenticationService.responseToken(refreshTokenRequest));
    }
}
