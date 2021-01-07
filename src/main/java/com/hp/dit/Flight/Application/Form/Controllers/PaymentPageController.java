package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.entities.UserTranactionEntity;
import com.hp.dit.Flight.Application.Form.enums.PaymentMode;
import com.hp.dit.Flight.Application.Form.enums.PaymentStatus;
import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;
import com.hp.dit.Flight.Application.Form.form.PaymentForm;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentCallback;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentDetail;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentUtil;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import com.hp.dit.Flight.Application.Form.services.UserTransactionService;
import com.hp.dit.Flight.Application.Form.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class PaymentPageController {

    @Autowired
    private FlightFormService flightFormService;

    @Autowired
    UserTransactionService userTransactionService;


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
    public String paymentResponse(Model model, HttpServletRequest request,
                                  @RequestParam String mihpayid,
                                  @RequestParam String status,
                                  @RequestParam String txnid,
                                  @RequestParam PaymentMode mode,
                                  @RequestParam String hash,
                                  @RequestParam String key,
                                  @RequestParam String amount,
                                  @RequestParam String productinfo,
                                  @RequestParam String firstname,
                                  @RequestParam String email,
                                  @RequestParam String phone,
                                  @RequestParam String error,
                                  @RequestParam String bank_ref_num) {
        try {

            PaymentCallback paymentCallback = new PaymentCallback();
            if (Utilities.empty(amount)) {
                paymentCallback.setAmount("");
            } else {
                paymentCallback.setAmount(amount);
            }

            if (Utilities.empty(mihpayid)) {
                paymentCallback.setMihpayid("");
            } else {
                paymentCallback.setMihpayid(mihpayid);
            }

            if (Utilities.empty(status)) {
                paymentCallback.setStatus(PaymentStatus.Failed.toString());
            } else {
                paymentCallback.setStatus(status);
            }

            if (Utilities.empty(txnid)) {
                paymentCallback.setTxnid("");
            } else {
                paymentCallback.setTxnid(txnid);
            }

            if (Utilities.empty(hash)) {
                paymentCallback.setHash("");
            } else {
                paymentCallback.setHash(hash);
            }

            if (Utilities.empty(key)) {
                paymentCallback.setKey("");
            } else {
                paymentCallback.setKey(key);
            }

            if (Utilities.empty(productinfo)) {
                paymentCallback.setProductinfo("");
            } else {
                paymentCallback.setProductinfo(productinfo);
            }

            if (Utilities.empty(firstname)) {
                paymentCallback.setFirstname("");
            } else {
                paymentCallback.setFirstname(firstname);
            }

            if (Utilities.empty(email)) {
                paymentCallback.setEmail("");
            } else {
                paymentCallback.setEmail(email);
            }

            if (Utilities.empty(phone)) {
                paymentCallback.setPhone("");
            } else {
                paymentCallback.setPhone(phone);
            }

            if (Utilities.empty(error)) {
                paymentCallback.setError(error);
            } else {
                paymentCallback.setError(error);
            }

            if (Utilities.empty(bank_ref_num)) {
                paymentCallback.setBank_ref_num("");
            } else {
                paymentCallback.setBank_ref_num(bank_ref_num);
            }


            //Verify the Payment and then save to Database

            if (PaymentUtil.verifyPayment(paymentCallback)) {

                System.out.println("Return Success Page");
                //Save data to Database
                UserTranactionEntity entity = new UserTranactionEntity();
                entity.setActive(true);
                entity.setAmount(paymentCallback.getAmount());
                entity.setBankRefNumber(paymentCallback.getBank_ref_num());
                entity.setEmail(paymentCallback.getEmail());
                entity.setError(paymentCallback.getError());
                entity.setMihpayId(paymentCallback.getMihpayid());
                entity.setName(paymentCallback.getFirstname());
                entity.setPaymentMode(paymentCallback.getMode().name());
                entity.setPaymentStatus(paymentCallback.getStatus());
                entity.setTransactionId(paymentCallback.getTxnid());
                entity.setUserId(Integer.parseInt(paymentCallback.getProductinfo()));
                entity.setPhone(paymentCallback.getPhone());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                entity.setCreatedDate(date);
                userTransactionService.saveTransaction(entity);
                return "paymentResponse";
            } else {
                System.out.println("Return Fail Page");
                return "paymentResponse";
            }


        } catch (Exception ex) {
            // request.getSession().setAttribute("serverError", ex.getLocalizedMessage().toString());
            return "paymentResponse";
        }


    }


}