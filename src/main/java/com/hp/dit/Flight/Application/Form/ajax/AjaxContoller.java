package com.hp.dit.Flight.Application.Form.ajax;


import com.hp.dit.Flight.Application.Form.entities.*;
import com.hp.dit.Flight.Application.Form.modal.RolesModal;
import com.hp.dit.Flight.Application.Form.repositories.RolesRepository;
import com.hp.dit.Flight.Application.Form.services.*;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.AbstractJaxb2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.*;

@Controller
public class AjaxContoller {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserTypeService userTypeService;

    @Autowired
    ReservationTypeService reservationTypeService;

    @Autowired
    RelationshipPrefixService relationshipPrefixService;

    @Autowired
    ReasonAvailingFlightService reasonAvailingFlightService;

    @Autowired
    DistrictService districtService;

    @Autowired
    HelipadService helipadService;

    private static final Logger logger = LoggerFactory.getLogger(AjaxContoller.class);


    @RequestMapping(value = "/ajax/getRoles", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getRoles() {
        Map<String, Object> map = null;
        List<Object[] > roles = rolesRepository.getRoles();
        List<RolesModal> modelRole = new ArrayList<>();


        for (Object[] result : roles) {
            RolesModal pojo = new RolesModal();
            pojo.setRole_id((BigInteger) result[0]);
            pojo.setRole_name((String) result[1]);
            modelRole.add(pojo);
        }

      //  System.out.println(roles.get(0).getId() + " fdfdfd" + roles.get(0).getName());
        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, modelRole);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }

    @RequestMapping(value = "/ajax/getUserType", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getUserType() throws Exception {
        Map<String, Object> map = null;
        List<UserType> getUserType = userTypeService.getActiveUserType();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, getUserType);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }


    @RequestMapping(value = "/ajax/registrationType", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getRegistrationType() throws Exception {
        Map<String, Object> map = null;
        List<RegistrationType> listRegistrationType = reservationTypeService.getResevationType();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, listRegistrationType);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }

    @RequestMapping(value = "/ajax/relationshipPrefix", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getRelationshipPrefix() throws Exception {
        Map<String, Object> map = null;
        List<RelationshipPrefix> listRegistrationType = relationshipPrefixService.getRelationshipPrefixes();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, listRegistrationType);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }

    @RequestMapping(value = "/ajax/reasonAvailingFlightService", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getReasonAvailingFlightService() throws Exception {
        Map<String, Object> map = null;
        List<ReasonAvailingFlight> listRegistrationType = reasonAvailingFlightService.getReasonAvailingFlight();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, listRegistrationType);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }


    @RequestMapping(value = "/ajax/flightDistrictToGoFrom", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getFlightDistrictToGoFrom() throws Exception {
        Map<String, Object> map = null;
        List<District> listRegistrationType = districtService.getDistricts();

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, listRegistrationType);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }

    @RequestMapping(value = "/ajax/flightHelipadNameToGoFrom", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getFlightHelipadNameToGoFrom(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;
        List<Helipad> barriers = helipadService.getDistricts(Integer.parseInt(id));

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, barriers);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }

}
