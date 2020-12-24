package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.RegistrationType;
import com.hp.dit.Flight.Application.Form.entities.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationTypeRepository extends CrudRepository<RegistrationType,Integer> {

    @Query(value="select * from mst_reservationtype where active=true", nativeQuery = true)
    List<RegistrationType> getActiveType() throws Exception;

}