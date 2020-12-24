package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.Helipad;
import com.hp.dit.Flight.Application.Form.repositories.HelipadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelipadService  {

    @Autowired
    HelipadRepository helipadRepository;

    public HelipadRepository getHelipadRepository() {
        return helipadRepository;
    }

    public void setHelipadRepository(HelipadRepository helipadRepository) {
        this.helipadRepository = helipadRepository;
    }

    public List<Helipad> getDistricts(int district_id)  {
        List<Helipad> districts = null;
        try {
            districts = helipadRepository.findByStateId(district_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districts;
    }
}
