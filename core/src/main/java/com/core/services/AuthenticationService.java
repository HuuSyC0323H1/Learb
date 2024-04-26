package com.core.services;

import com.core.api.forms.RequestSignIn;
import com.core.api.forms.RequestSignUp;
import com.core.api.view.AuthenticationResponse;
import com.core.dto.RefreshTokenRequest;
import com.core.model.User;

/**
 * @author: Huu Sy
 * @Date: 26/04/2024 3:07 SA
 */

public interface AuthenticationService {

    User signup(RequestSignUp requestSignUp);

    AuthenticationResponse signIn(RequestSignIn requestSignIn);

    AuthenticationResponse responseToken(RefreshTokenRequest refreshTokenRequest);
}
