package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.userFormDataPreviousServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userFormDataPreviousServiceRepository extends CrudRepository<userFormDataPreviousServiceEntity,Integer> {

}
