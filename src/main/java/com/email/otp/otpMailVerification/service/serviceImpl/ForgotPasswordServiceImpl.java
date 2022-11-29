package com.email.otp.otpMailVerification.service.serviceImpl;

import com.email.otp.otpMailVerification.service.EmailService;
import com.email.otp.otpMailVerification.service.ForgotPasswordService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
   @Autowired
    EmailService emailService;
    private static final Integer EXPIRE_MINS = 1;
    private final LoadingCache<String,Object> otpCache;
    int min = 1000;
    int max = 9999;

    public ForgotPasswordServiceImpl(){
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Object>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }
    @Override
    public String generateOtp(String email) {
        int otp = (int) (Math.random()*(max-min+1)+min);
        System.out.println("otp is "+ otp);
       String subject = "OTP from witzeal";
       String msg = "your OTP is -"+otp +" ";
        otpCache.put("myOtp", otp);
        otpCache.put("email", email);
        //otpCache.put("mailSentDate", new Date());
        return emailService.sendSimpleMail(subject,msg,email);
        ///return "test";
    }

    @Override
    public int getOtp(String email) {
        try{
            return  (int)otpCache.get("myOtp");
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public String verifyOtpotp(int otp) {
        try {
           int oldOtp = (int) otpCache.get("myOtp");
           if (otp==oldOtp){
               //no need of commented code
//               Date oldDate = (Date) otpCache.get("mailSentDate");
//               Date currentDate = new Date();
//               long diff = currentDate.getTime() - oldDate.getTime();
//               long diffInMint = diff/(1000 * 60);
//               if (diffInMint>EXPIRE_MINS){
//                   return "Otp Time has been expired";
//               }
               String email = String.valueOf(otpCache.get("email"));
               //find user by email
               return "verify success";
           }
           else {
               return "Invalid otp";
           }
        }catch (Exception e){
            e.printStackTrace();
            return "Caught an exception";
        }

    }
}
