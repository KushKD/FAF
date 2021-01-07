package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.form.ViewApplications;
import com.hp.dit.Flight.Application.Form.projections.FormDataListProjection;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import com.hp.dit.Flight.Application.Form.validators.ViewApplicationsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterApplications {

    @Autowired
    private ViewApplicationsValidator viewApplicationsValidator;

    @Autowired
    private FlightFormService flightFormService;

    private static final Logger logger = LoggerFactory.getLogger(FilterApplications.class);

    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public String applicationsFilter(Model model) {
        model.addAttribute("viewApplications", new ViewApplications());
        return "applications";
    }






    @RequestMapping(value = "/filterApplications", method = RequestMethod.POST)
    public String getIdCardList(@ModelAttribute("viewApplications") ViewApplications applications, BindingResult bindingResult, Model model, HttpServletRequest request) {
        viewApplicationsValidator.validate(applications, bindingResult);

        if (bindingResult.hasErrors()) {
            return "applications";
        }
        try {
            List<Object[]> data = null;
            if(applications.getAppliactionStatus().equalsIgnoreCase("A")){
                data = flightFormService.getProjectionApplicationList(Integer.parseInt(applications.getLocation()),
                        Integer.parseInt(applications.getHelipadName()), applications.getDate().trim(), Constants.APPROVED);
            }else if(applications.getAppliactionStatus().equalsIgnoreCase("P")){
                data = flightFormService.getProjectionApplicationList(Integer.parseInt(applications.getLocation()),
                        Integer.parseInt(applications.getHelipadName()), applications.getDate().trim(),Constants.PENDING);
            }else if(applications.getAppliactionStatus().equalsIgnoreCase("R")){
                data = flightFormService.getProjectionApplicationList(Integer.parseInt(applications.getLocation()),
                        Integer.parseInt(applications.getHelipadName()), applications.getDate().trim(),Constants.REJECTED);
            }else{
                data = flightFormService.getProjectionApplicationList(Integer.parseInt(applications.getLocation()),
                        Integer.parseInt(applications.getHelipadName()), applications.getDate().trim(),Constants.PENDING);
            }
            if (!data.isEmpty()) {

                List<FormDataListProjection> projectionData = new ArrayList<>();


                for (Object[] result : data) {
                    FormDataListProjection pojo = new FormDataListProjection();
                    pojo.setUserId((Integer) result[0]);
                    pojo.setFullName((String) result[1]);
                    pojo.setMobileNumber((BigInteger) result[2]);
                    pojo.setApplicationStatus((String) result[3]);
                    pojo.setPaymentStatus((String)result[4]);
                    projectionData.add(pojo);
                }



                request.getSession().setAttribute("successMessage", "Data found Successfully");
                model.addAttribute("applications", projectionData);
                model.addAttribute("helipadName", applications.getHelipadName());
                model.addAttribute("location", applications.getLocation());
                applications.setDate(applications.getDate());
                applications.setHelipadName(applications.getHelipadName());
                applications.setLocation(applications.getLocation());
                return "applications";
            } else {
                model.addAttribute("helipadName", applications.getHelipadName());
                model.addAttribute("location", applications.getLocation());
                applications.setDate(applications.getDate());
                applications.setHelipadName(applications.getHelipadName());
                applications.setLocation(applications.getLocation());
                model.addAttribute("serverError", "No Data available for the current District and Barrier");
                return "applications";
            }


        } catch (Exception ex) {
            applications.setDate("");
            applications.setHelipadName("");
            applications.setLocation("");
            model.addAttribute("serverError", ex.toString());
            return "applications";
        }



    }
}
