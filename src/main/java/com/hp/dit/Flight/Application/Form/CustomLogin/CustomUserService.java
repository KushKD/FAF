package com.hp.dit.Flight.Application.Form.CustomLogin;


import com.hp.dit.Flight.Application.Form.entities.UserEntity;

public interface CustomUserService {


    void save(UserEntity user);
    UserEntity findByUsername(String userName);
    UserEntity findByMobileNumber(String mobileNumber);
}
