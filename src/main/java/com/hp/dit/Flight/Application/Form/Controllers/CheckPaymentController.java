package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.form.CheckPayment;
import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Controller
public class CheckPaymentController {


    @RequestMapping(value = "/checkpayment", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("successMessage", "");
        model.addAttribute("checkPayment", new CheckPayment());
        return "checkpayment";
    }


    @RequestMapping(value = "/checkPaymentPost", method = RequestMethod.POST)
    public String saveDetails(@ModelAttribute("checkPayment") CheckPayment checkPayment,
                               Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
      //  flightFormValidator.validate(flightApplicationForm, bindingResult);

        PaymentUtil util = new PaymentUtil();
        String status = util.verifyPayment(checkPayment.getApplication_id());

            request.getSession().setAttribute("successMessage", status);



        return  "checkpayment";
    }
}
