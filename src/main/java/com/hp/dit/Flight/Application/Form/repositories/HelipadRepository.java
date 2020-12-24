package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.Helipad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelipadRepository  extends CrudRepository<Helipad,Integer> {

    @Query(value="select * from mst_helipad where district_id=:districtId AND active=true", nativeQuery = true)
    List<Helipad> findByStateId(@Param("districtId") int districtId) throws Exception;

}
