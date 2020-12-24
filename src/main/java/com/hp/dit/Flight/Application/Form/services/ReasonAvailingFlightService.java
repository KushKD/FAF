package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.ReasonAvailingFlight;
import com.hp.dit.Flight.Application.Form.entities.RelationshipPrefix;
import com.hp.dit.Flight.Application.Form.repositories.ReasonAvailingFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReasonAvailingFlightService {

    @Autowired
    ReasonAvailingFlightRepository reasonAvailingFlightRepository;

    public ReasonAvailingFlightRepository getReasonAvailingFlightRepository() {
        return reasonAvailingFlightRepository;
    }

    public void setReasonAvailingFlightRepository(ReasonAvailingFlightRepository reasonAvailingFlightRepository) {
        this.reasonAvailingFlightRepository = reasonAvailingFlightRepository;
    }

    public List<ReasonAvailingFlight> getReasonAvailingFlight(){
        List<ReasonAvailingFlight> TypeList = null;
        try {
            TypeList = reasonAvailingFlightRepository.getReasonAvailingFlight();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error== getting the UserType");
        }
        return TypeList;
    }
}
