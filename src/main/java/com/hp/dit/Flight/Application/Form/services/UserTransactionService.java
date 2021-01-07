package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.UserTranactionEntity;
import com.hp.dit.Flight.Application.Form.repositories.UserTranactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.UserTransaction;

@Service
public class UserTransactionService {

    @Autowired
    UserTranactionRepository userTranactionRepository;

    public UserTranactionRepository getUserTranactionRepository() {
        return userTranactionRepository;
    }

    public void setUserTranactionRepository(UserTranactionRepository userTranactionRepository) {
        this.userTranactionRepository = userTranactionRepository;
    }

    public UserTranactionEntity saveTransaction(UserTranactionEntity data){
        return userTranactionRepository.save(data);
    }
}
