package com.hp.dit.Flight.Application.Form.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class HomeController {




    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainpage(Model model) {
        return "mainpage";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String homePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            for (GrantedAuthority authority : authorities) {
                System.out.println(authority.getAuthority().toString());
            }

            System.out.println(username);
            return "homepage_new";
        }
    }

    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    public String gallery(Model model) {
        return "gallery";
    }

    @RequestMapping(value = "/contactus", method = RequestMethod.GET)
    public String contactus(Model model) {
        return "contactus";
    }

    @RequestMapping(value = "/refundpage", method = RequestMethod.GET)
    public String refundpage(Model model) {
        return "refundpage";
    }

    @RequestMapping(value = "/privacypolicy", method = RequestMethod.GET)
    public String privacypolicy(Model model) {
        return "privacypolicy";
    }

    @RequestMapping(value = "/termsandconditions", method = RequestMethod.GET)
    public String termsandconditions(Model model) {
        return "termsandconditions";
    }












}
