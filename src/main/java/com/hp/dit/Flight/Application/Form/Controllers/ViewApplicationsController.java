package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.projections.FormDataListProjection;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewApplicationsController {

    private static final Logger logger = LoggerFactory.getLogger(ViewApplicationsController.class);

    @Autowired
    private FlightFormService flightFormService;

    @RequestMapping(value = "/applications_all", method = RequestMethod.GET)
    public String showIdCardList(Model model, HttpServletRequest request) {
        List<Object[]> data = null;
        data = flightFormService.getAllApplications();

        if (!data.isEmpty()) {

            List<FormDataListProjection> projectionData = new ArrayList<>();


            for (Object[] result : data) {
                FormDataListProjection pojo = new FormDataListProjection();
                pojo.setUserId((Integer) result[0]);
                pojo.setFullName((String) result[1]);
                pojo.setMobileNumber((BigInteger) result[2]);
                pojo.setApplicationStatus((String) result[3]);
                pojo.setPaymentStatus((String) result[4]);
                projectionData.add(pojo);
            }


            request.getSession().setAttribute("successMessage", "Data found Successfully");
            model.addAttribute("applications", projectionData);
            return "applications_all";
        }else{
            model.addAttribute("serverError", "No Data available for the current District and Barrier");

            return "applications_all";
        }



    }
}
