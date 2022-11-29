package com.email.otp.otpMailVerification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OtpMailVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpMailVerificationApplication.class, args);
	}

}
