package com.solona.Controller;

import com.solona.domain.USER_ROLE;
import com.solona.modal.VerificationCode;
import com.solona.response.ApiResponse;
import com.solona.response.AuthResponse;
import com.solona.response.SignupRequest;
import com.solona.modal.User;
import com.solona.repository.UserRepository;
import com.solona.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody SignupRequest req) throws Exception {
//        User user = new User();
//        user.setEmail(req.getEmail());
//        user.setFullName(req.getFullName());
//
//        User savedUser = userRepository.save(user);
        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse>createUserHandler(@RequestBody VerificationCode req) throws Exception {

        authService.sentLoginOtp(req.getEmail());
        ApiResponse res = new ApiResponse();
        res.setMessage("Otp sent successfully");
        return ResponseEntity.ok(res);
    }
}
