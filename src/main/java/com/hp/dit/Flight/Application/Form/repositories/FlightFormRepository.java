package com.hp.dit.Flight.Application.Form.repositories;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.projections.FormDataListProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightFormRepository extends CrudRepository<FlightFormEntity,Integer> {

    @Query(value = "select * from public.userformdata where  district_id=:location AND helipad_id=:helipad AND active = true AND application_status= :status  AND  to_char(createddate, 'DD/MM/YYYY') = :selectedDate order by createddate desc", nativeQuery = true)
    List<FlightFormEntity> fetchApplications(int location, int helipad, String selectedDate, String status);

    @Query(value = "SELECT user_id,full_name,mobile_number,application_status  from public.userformdata where  district_id=:location AND helipad_id=:helipad AND active = true AND application_status= :status  AND  to_char(createddate, 'DD/MM/YYYY') = :selectedDate order by createddate desc", nativeQuery = true)
    List<Object[]> getApplicationProjection(int location, int helipad, String selectedDate, String status);

    @Query(value = "SELECT * from public.userformdata where  user_id=:id ", nativeQuery = true)
    FlightFormEntity getUserDetialsByID(int id);

    @Query(value = "select * from public.userformdata where  user_id=:userId ", nativeQuery = true)
    FlightFormEntity getApplicationViaUserID(int userId);

    @Query(value = "SELECT user_id,full_name,mobile_number,application_status  from public.userformdata where active = true  order by createddate desc", nativeQuery = true)
    List<Object[]> getAllApplications();

    @Query(value = "SELECT application_status, comments ,user_id from public.userformdata where active = true AND  user_id=:userId", nativeQuery = true)
    List<Object[]> checkStatus(int userId);


}
