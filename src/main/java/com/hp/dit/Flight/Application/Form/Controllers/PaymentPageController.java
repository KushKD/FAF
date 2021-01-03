package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentPageController {

    @Autowired
    private FlightFormService flightFormService;


    private static final Logger logger = LoggerFactory.getLogger(PaymentPageController.class);

    @RequestMapping(value = "/paymentpage", method = RequestMethod.GET)
    public String paymentpage(Model model) {
        Integer userId = (Integer) model.asMap().get("userID");
        System.out.println(userId);
        FlightFormEntity user = new FlightFormEntity();
        try {
            user = flightFormService.getDataByUserID(userId);
            if (user != null) {
                System.out.println(user.toString());
                model.addAttribute("user", user);
                return "paymentpage";
            } else {
                //request.getSession().setAttribute("successMessage", "No Data Available.");
                return "paymentpage";
            }
        } catch (Exception ex) {
           // request.getSession().setAttribute("serverError", ex.getLocalizedMessage().toString());
        }

        return "userdetails";
    }
}