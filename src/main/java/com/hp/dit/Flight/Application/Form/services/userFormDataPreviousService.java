package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.userFormDataPreviousServiceEntity;
import com.hp.dit.Flight.Application.Form.repositories.userFormDataPreviousServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userFormDataPreviousService {

    @Autowired
    userFormDataPreviousServiceRepository userFormDataPreviousServiceRepository;

    public userFormDataPreviousServiceRepository getUserFormDataPreviousServiceRepository() {
        return userFormDataPreviousServiceRepository;
    }

    public void setUserFormDataPreviousServiceRepository(com.hp.dit.Flight.Application.Form.repositories.userFormDataPreviousServiceRepository userFormDataPreviousServiceRepository) {
        this.userFormDataPreviousServiceRepository = userFormDataPreviousServiceRepository;
    }


    public void saveData(List<userFormDataPreviousServiceEntity> list){
         userFormDataPreviousServiceRepository.saveAll(list);

    }
}
