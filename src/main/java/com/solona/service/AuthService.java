package com.solona.service;

import com.solona.request.LoginRequest;
import com.solona.response.AuthResponse;
import com.solona.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req);
}
