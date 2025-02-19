package com.bank.authentication.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "${email-service.url}")
public interface EmailSenderService {

    @PostMapping("/email/send-otp")
    String sendOtpEmail(@RequestBody OtpRequest otpRequest);

}



