package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.ReasonAvailingFlight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReasonAvailingFlightRepository extends CrudRepository<ReasonAvailingFlight,Integer> {

    @Query(value="select * from mst_reasonavailing_flight where active=true", nativeQuery = true)
    List<ReasonAvailingFlight> getReasonAvailingFlight() throws Exception;
}
