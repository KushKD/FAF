package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.RegistrationType;
import com.hp.dit.Flight.Application.Form.entities.RelationshipPrefix;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipPrefixRepository extends CrudRepository<RelationshipPrefix,Integer> {

        @Query(value="select * from mst_relationshipprefix where active=true", nativeQuery = true)
        List<RelationshipPrefix> getRelationshipPrefixes() throws Exception;

    }