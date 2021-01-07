package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.UserTranactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTranactionRepository extends CrudRepository<UserTranactionEntity,Integer> {

    @Query(value = "select * from user_transaction where user_id=:id AND active = true ", nativeQuery = true)
    UserTranactionEntity getUserTransactions(int id);

}
