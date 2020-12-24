package com.hp.dit.Flight.Application.Form.services;

import com.hp.dit.Flight.Application.Form.entities.RegistrationType;
import com.hp.dit.Flight.Application.Form.entities.UserType;
import com.hp.dit.Flight.Application.Form.repositories.ReservationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationTypeService {

    @Autowired
    ReservationTypeRepository reservationTypeRepository;

    public ReservationTypeRepository getReservationTypeRepository() {
        return reservationTypeRepository;
    }

    public void setReservationTypeRepository(ReservationTypeRepository reservationTypeRepository) {
        this.reservationTypeRepository = reservationTypeRepository;
    }

    public List<RegistrationType> getResevationType(){
        List<RegistrationType> TypeList = null;
        try {
            TypeList = reservationTypeRepository.getActiveType();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error== getting the UserType");
        }
        return TypeList;
    }
}
