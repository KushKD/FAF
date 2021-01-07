package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.UserTranactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTranactionRepository extends CrudRepository<UserTranactionEntity,Integer> {


}
