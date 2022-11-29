package com.email.otp.otpMailVerification.controller;

import com.email.otp.otpMailVerification.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class ForgotPasswordController {
    @Autowired
    private ForgotPasswordService forgotPasswordService;
    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam("email") String email ){
        System.out.println(email);
        return forgotPasswordService.generateOtp(email);
    }
    @GetMapping("/getOtp")
    public int getOtp(@RequestParam("email") String email){
        return forgotPasswordService.getOtp(email);
    }
    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("otp") int otp) throws ExecutionException {
        return forgotPasswordService.verifyOtpotp(otp);
    }
}
