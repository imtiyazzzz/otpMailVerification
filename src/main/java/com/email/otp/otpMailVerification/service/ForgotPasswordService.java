package com.email.otp.otpMailVerification.service;

import java.util.concurrent.ExecutionException;

public interface ForgotPasswordService {
    public String generateOtp(String email);

    public int getOtp(String email);

    public String verifyOtpotp(int otp) throws ExecutionException;
}
