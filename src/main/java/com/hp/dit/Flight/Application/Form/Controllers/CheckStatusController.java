package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.form.RegisterUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CheckStatusController {

    private static final Logger logger = LoggerFactory.getLogger(CheckStatusController.class);

    @RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
    public String checkStatus(Model model) {
        model.addAttribute("registerUser", new RegisterUser());
        return "checkStatus";
    }
}
