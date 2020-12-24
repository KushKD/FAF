package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository  extends CrudRepository<UserType,Integer> {

    @Query(value="select * from mst_typeuser where active=true", nativeQuery = true)
    List<UserType> getActiveTypeUser() throws Exception;

}
