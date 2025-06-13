package com.solona.Controller;

import com.solona.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeControllerHandler(){
        ApiResponse apiresponse = new ApiResponse();
        apiresponse.setMessage("Welcome to ecommerce multi vendor System");
        return apiresponse;
    }
}
