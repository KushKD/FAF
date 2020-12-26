package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightFormRepository extends CrudRepository<FlightFormEntity,Integer> {
}
