package com.hp.dit.Flight.Application.Form.CustomLogin;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String userName, String password);
}