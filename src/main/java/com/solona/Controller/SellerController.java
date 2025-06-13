package com.solona.Controller;

import com.solona.modal.VerificationCode;
import com.solona.repository.VerificationCodeRepository;
import com.solona.response.ApiResponse;
import com.solona.response.AuthResponse;
import com.solona.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    public ResponseEntity<AuthResponse> loginSeller(
            @RequestBody VerificationCode req
            ) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
        if(verificationCode == null){
            throw new Exception("wrong otp...");
        }
        return null;
    }

}
