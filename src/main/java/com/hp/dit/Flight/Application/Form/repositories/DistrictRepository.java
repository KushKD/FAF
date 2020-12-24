package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.District;
import com.hp.dit.Flight.Application.Form.entities.ReasonAvailingFlight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends CrudRepository<District,Integer> {

    @Query(value="select * from mst_district where active=true", nativeQuery = true)
    List<District> getDistrict() throws Exception;
}
