package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightFormRepository extends CrudRepository<FlightFormEntity,Integer> {

    @Query(value = "select * from public.userformdata where  district_id=:location AND helipad_id=:helipad AND active = true AND  to_char(createddate, 'DD/MM/YYYY') = :selectedDate order by createddate desc", nativeQuery = true)
    List<FlightFormEntity> getDataViaLocationBarrier(int location, int helipad, String selectedDate);
}
