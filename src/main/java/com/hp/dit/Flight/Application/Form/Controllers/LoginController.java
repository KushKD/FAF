package com.hp.dit.Flight.Application.Form.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

   // @Autowired
   // private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
 //       System.out.println("This is Login Controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            if (error != null)
                model.addAttribute("error", "Your username and password is invalid.");

            if (logout != null)
                model.addAttribute("message", "You have been logged out successfully.");

            return "login";
        }
    }





    @RequestMapping(value =  "/logout" , method = RequestMethod.GET)
    public String logout(Model model) {
        System.out.println("Logout Successful");
      //  model.addAttribute("login", new Lo());
        return "login";
    }
}
