package com.hp.dit.Flight.Application.Form.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentPageController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentPageController.class);

    @RequestMapping(value = "/paymentpage", method = RequestMethod.GET)
    public String paymentpage(Model model) {
        //model.addAttribute("registerUser", new RegisterUser());
        return "paymentpage";
    }
}
