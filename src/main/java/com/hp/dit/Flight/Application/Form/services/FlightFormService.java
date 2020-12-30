package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.projections.FormDataListProjection;
import com.hp.dit.Flight.Application.Form.repositories.FlightFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<FlightFormEntity> getApplications(int location, int helipad, String date, String status) {
        return  flightFormRepository.fetchApplications(location,helipad,date, status);
    }

    public List<Object[]> getProjectionApplicationList(int location, int helipad, String date, String status){
        return flightFormRepository.getApplicationProjection(location,helipad,date,status);
    }

    public FlightFormEntity getDataByUserID(int id){
        return flightFormRepository.getUserDetialsByID(id);
    }

    public FlightFormEntity getCompleteApplication(int id){
        return flightFormRepository.getApplicationViaUserID(id);
    }
}
