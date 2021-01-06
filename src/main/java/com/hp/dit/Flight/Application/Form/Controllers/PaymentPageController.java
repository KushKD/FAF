package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;
import com.hp.dit.Flight.Application.Form.form.PaymentForm;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentCallback;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentDetail;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentUtil;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentPageController {

    @Autowired
    private FlightFormService flightFormService;


    private static final Logger logger = LoggerFactory.getLogger(PaymentPageController.class);

    @RequestMapping(value = "/paymentpage", method = RequestMethod.GET)
    public String paymentpage(Model model) {
        model.addAttribute("paymentForm", new PaymentForm());
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

        return "paymentpage";
    }

    @RequestMapping(value = "/paymentpagepost", method = RequestMethod.POST)
    public String postpaymentPage(@ModelAttribute("paymentForm") PaymentForm paymentForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request) {
        try {
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setAmount(paymentForm.getAmount());
        paymentDetail.setName(paymentForm.getName());
        paymentDetail.setProductInfo(paymentForm.getUser_id_transaction_id());
        paymentDetail.setPhone(paymentForm.getPhone());
        paymentDetail.setEmail("kushkumardhawan@gmail.com");

        paymentDetail = PaymentUtil.populatePaymentDetail(paymentDetail);

            request.getSession().setAttribute("merchant_key", paymentDetail.getKey());
            request.getSession().setAttribute("hash", paymentDetail.getHash());
            request.getSession().setAttribute("txnid", paymentDetail.getTxnId());
            request.getSession().setAttribute("amount", paymentDetail.getAmount());
            request.getSession().setAttribute("firstname", paymentDetail.getName());
            request.getSession().setAttribute("email", paymentDetail.getEmail());
            request.getSession().setAttribute("phone", paymentDetail.getPhone());
            request.getSession().setAttribute("productinfo", paymentDetail.getProductInfo());
            request.getSession().setAttribute("surl", paymentDetail.getsUrl());
            request.getSession().setAttribute("furl", paymentDetail.getfUrl());




            return "postPayment";

        } catch (Exception ex) {
            // request.getSession().setAttribute("serverError", ex.getLocalizedMessage().toString());
            return "paymentpage";
        }


    }


    @RequestMapping(value = "/paymentResponse", method = RequestMethod.POST)
    public String paymentResponse(Model model, HttpServletRequest request) {
        try {


            System.out.println(request.getSession().getAttribute("status"));
            System.out.println(request.getSession().getAttribute("hash"));


            return null;

        } catch (Exception ex) {
            // request.getSession().setAttribute("serverError", ex.getLocalizedMessage().toString());
            return null;
        }


    }











}