package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.OTPEntity;
import com.hp.dit.Flight.Application.Form.repositories.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired
    OTPRepository otpRepository;

    public OTPRepository getOtpRepository() {
        return otpRepository;
    }

    public void setOtpRepository(OTPRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public void saveOTP(OTPEntity otp){
        otpRepository.save(otp);
    }
}
