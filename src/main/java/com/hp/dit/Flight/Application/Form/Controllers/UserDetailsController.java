package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.entities.UserTranactionEntity;
import com.hp.dit.Flight.Application.Form.form.ActionForm;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import com.hp.dit.Flight.Application.Form.services.UserTransactionService;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

    @Autowired
    private FlightFormService flightFormService;

    @Autowired
    private UserTransactionService userTransactionService;

    @RequestMapping(value = "/getUserDetails/{id}", method= RequestMethod.GET)
    public String getUserDetailComplete(@PathVariable("id")int id, Model model, HttpServletRequest request) {



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("actionForm", new ActionForm());
            FlightFormEntity user = new FlightFormEntity();
            UserTranactionEntity transactionUser = new UserTranactionEntity();
            try {
                user = flightFormService.getDataByUserID(id);
                if (user != null) {
                    System.out.println(user.toString());
                    System.out.println(transactionUser.toString());
                    transactionUser =userTransactionService.getUserTransaction(user.getUserId());
                    model.addAttribute("userdata", user);
                    model.addAttribute("transactionUser", transactionUser);
                    request.getSession().setAttribute("successMessage", "Data found Successfully");
                    return "userdetails";
                } else {
                    request.getSession().setAttribute("successMessage", "No Data Available.");
                    return "userdetails";
                }

            } catch (Exception ex) {
                request.getSession().setAttribute("serverError", ex.getLocalizedMessage().toString());
            }

            return "userdetails";
        }

    }


    @RequestMapping(value = "/updateApplication", method = RequestMethod.POST)
    public String update_application(@ModelAttribute("actionForm") ActionForm actionForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        // roleValidator.validate(roleForm, bindingResult);
        System.out.println(actionForm.toString());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            try {
                FlightFormEntity user = new FlightFormEntity();
                user = flightFormService.getCompleteApplication(Integer.parseInt(actionForm.getUser_id()));
                if (user != null) {
                    if (actionForm.getAction().equalsIgnoreCase("A")) {
                        user.setApplicaionStatus(Constants.APPROVED);
                    } else if (actionForm.getAction().equalsIgnoreCase("R")) {
                        user.setApplicaionStatus(Constants.REJECTED);
                    } else {
                        user.setApplicaionStatus(Constants.PENDING);
                    }
                    user.setConcernedAuthorityComments(actionForm.getComments());
                }

                FlightFormEntity savedData = flightFormService.saveUser(user);
                model.addAttribute("userdata", savedData);
                model.addAttribute("successMessage", "Application Updated");
                return "userdetails";

            } catch (Exception ex) {

                model.addAttribute("serverError", ex.toString());
                return "userdetails";
            }
        }


    }

}
