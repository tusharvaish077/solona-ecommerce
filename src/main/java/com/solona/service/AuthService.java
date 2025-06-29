package com.solona.service;

import com.solona.domain.USER_ROLE;
import com.solona.request.LoginRequest;
import com.solona.response.AuthResponse;
import com.solona.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req) throws Exception;
}
