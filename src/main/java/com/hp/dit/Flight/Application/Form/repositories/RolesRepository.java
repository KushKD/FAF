package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.RolesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.List;
import java.util.Set;


@Repository
public interface RolesRepository extends CrudRepository<RolesEntity, Long> {

    @Query(value = "SELECT role_id, role_name from roles where active = true", nativeQuery = true)
    List<Object[]> getRoles();

    @Query(value = "SELECT * from roles where active = true AND role_name =:role_name_" , nativeQuery = true)
    RolesEntity checkRole(@Param("role_name_") String rolenmae);

}