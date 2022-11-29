package com.email.otp.otpMailVerification.service;

public interface EmailService {
    String sendSimpleMail(String subject,String message, String to);
}
