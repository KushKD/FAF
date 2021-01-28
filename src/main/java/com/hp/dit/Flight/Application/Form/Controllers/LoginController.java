package com.hp.dit.Flight.Application.Form.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes("counter")
public class LoginController {

   // @Autowired
   // private RestTemplate restTemplate;

   // @Autowired
   // private CaptchaGenerator captchaGenerator;

//    @ModelAttribute("counter")
//    public AtomicInteger failureCounter() {
//        return new AtomicInteger(0);
//    }

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(Model model, String error, String logout, HttpSession httpSession) {
 //       System.out.println("This is Login Controller");
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        Object message = httpSession.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            httpSession.removeAttribute("message");
        }

     //   AtomicInteger counter = (AtomicInteger) model.getAttribute("counter");
      //  System.out.println(counter);
//        if(counter.intValue() >= 0) {
//            Captcha captcha = captchaGenerator.createCaptcha(200, 50);
//            httpSession.setAttribute("captcha", captcha);
//            model.addAttribute("captchaEncode", CaptchaUtils.encodeBase64(captcha));
//        }

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "login";
//        } else {
//            if (error != null)
//                model.addAttribute("error", "Your username and password is invalid.");
//
//            if (logout != null)
//                model.addAttribute("message", "You have been logged out successfully.");
//
//            return "login";
//        }
        return "login";
    }





    @RequestMapping(value =  "/logout" , method = RequestMethod.GET)
    public String logout(Model model) {
        System.out.println("Logout Successful");
        return "login";
    }
}
