package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.repositories.FlightFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightFormService {

    @Autowired
    FlightFormRepository flightFormRepository;

    public FlightFormRepository getFlightFormRepository() {
        return flightFormRepository;
    }

    public void setFlightFormRepository(FlightFormRepository flightFormRepository) {
        this.flightFormRepository = flightFormRepository;
    }


    public FlightFormEntity saveUser(FlightFormEntity entity) {
        return flightFormRepository.save(entity);

    }
}
