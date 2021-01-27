package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.entities.OTPEntity;
import com.hp.dit.Flight.Application.Form.entities.UserTranactionEntity;
import com.hp.dit.Flight.Application.Form.enums.PaymentMode;
import com.hp.dit.Flight.Application.Form.enums.PaymentStatus;
import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;
import com.hp.dit.Flight.Application.Form.form.PaymentForm;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentCallback;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentDetail;
import com.hp.dit.Flight.Application.Form.paymentutility.PaymentUtil;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import com.hp.dit.Flight.Application.Form.services.OTPService;
import com.hp.dit.Flight.Application.Form.services.UserTransactionService;
import com.hp.dit.Flight.Application.Form.utilities.CalculateAmount;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import com.hp.dit.Flight.Application.Form.utilities.SMSServices;
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

    private static final Logger logger = LoggerFactory.getLogger(PaymentPageController.class);
    @Autowired
    UserTransactionService userTransactionService;
    @Autowired
    private FlightFormService flightFormService;

    @Autowired
    private OTPService otpService;

    private String otp_response = null;





    @RequestMapping(value = "/paymentpage", method = RequestMethod.GET)
    public String paymentpage(Model model, HttpServletRequest request) {
        model.addAttribute("paymentForm", new PaymentForm());
        Integer userId = (Integer) model.asMap().get("userID");
        System.out.println(userId);
        FlightFormEntity user = null;

        try {
            user = flightFormService.getDataByUserID(userId);
            if (user != null) {

                if(user.getAge()<=3){
                    System.out.println(user.toString());
                    PaymentDetail paymentDetail = new PaymentDetail();
                    //Calculate Amount
                    paymentDetail.setAmount(Double.parseDouble(CalculateAmount.calculateAmount(user)));
                    paymentDetail.setName(user.getFullName());
                    paymentDetail.setProductInfo(Integer.toString(user.getUserId()));
                    paymentDetail.setPhone(String.valueOf(user.getMobileNumber()));
                    paymentDetail.setEmail("dc-lah-hp@nic.in");

                    paymentDetail = PaymentUtil.populatePaymentDetail(paymentDetail);

                    //Save Data to Entity
                    UserTranactionEntity transactionEntity = new UserTranactionEntity();
                    transactionEntity.setUserId(user.getUserId());
                    transactionEntity.setEmail("dc-lah-hp@nic.in");
                    transactionEntity.setName(user.getFullName());
                    transactionEntity.setPhone(String.valueOf(user.getMobileNumber()));
                    transactionEntity.setAmount(CalculateAmount.calculateAmount(user));
                    transactionEntity.setPaymentStatus("");
                    transactionEntity.setTransactionId(paymentDetail.getTxnId());
                    transactionEntity.setActive(true);
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    Date date = new Date(timestamp.getTime());
                    transactionEntity.setCreatedDate(date);
                    transactionEntity.setPaymentdateresponse(date);
                    userTransactionService.saveTransaction(transactionEntity);


                    //SEND OTP TO CLIENT
                    SMSServices sms = new SMSServices();
                    String SMSServerCode = null;
                    otp_response = sms.sendOtpSMS(Constants.smsUsername,
                            Constants.smsPassword, Constants.otp_Message +" "+ user.getUserId() + Constants.otp_MessageEnd,
                            Constants.smsSenderId,String.valueOf(user.getMobileNumber()),Constants.smsSecureKey, Constants.templateId);

                    if (!otp_response.isEmpty()) {
                        System.out.println(otp_response);
                        SMSServerCode = otp_response.split(",")[0];

                        if(SMSServerCode.equalsIgnoreCase("402")){
                            OTPEntity otp = new OTPEntity();
                            otp.setCdac_response(otp_response);
                            otp.setPhone(String.valueOf(user.getMobileNumber()));
                            otp.setUserId(user.getUserId());
                            otp.setSent_message(Constants.otp_Message +" "+ user.getUserId() + Constants.otp_MessageEnd);
                            Timestamp timestampx = new Timestamp(System.currentTimeMillis());
                            Date datex = new Date(timestampx.getTime());
                            otp.setSendTime(datex);
                            otpService.saveOTP(otp);

                        }else{
                            //Save to DB no SMS
                            OTPEntity otp = new OTPEntity();
                            otp.setCdac_response(otp_response);
                            otp.setPhone(String.valueOf(user.getMobileNumber()));
                            otp.setUserId(user.getUserId());
                            otp.setSent_message(Constants.otp_Message +" "+ user.getUserId() + Constants.otp_MessageEnd);
                            Timestamp timestampy = new Timestamp(System.currentTimeMillis());
                            Date datey = new Date(timestampy.getTime());
                            otp.setSendTime(datey);
                            otpService.saveOTP(otp);
                        }

                    }else{
                        OTPEntity otp = new OTPEntity();
                        otp.setCdac_response("No Resonse From CDAC");
                        otp.setPhone(String.valueOf(user.getMobileNumber()));
                        otp.setUserId(user.getUserId());
                        otp.setSent_message(Constants.otp_Message +" "+ user.getUserId() + Constants.otp_MessageEnd);
                        Timestamp timestampz = new Timestamp(System.currentTimeMillis());
                        Date datez = new Date(timestampz.getTime());
                        otp.setSendTime(datez);
                        otpService.saveOTP(otp);
                    }


                    request.getSession().setAttribute("firstname", paymentDetail.getName());
                    request.getSession().setAttribute("phone", paymentDetail.getPhone());
                    request.getSession().setAttribute("productinfo", paymentDetail.getProductInfo());

                    model.addAttribute("user", user);
                    return "nopaymentpage";
                }else{
                    System.out.println(user.toString());
                    PaymentDetail paymentDetail = new PaymentDetail();
                    //Calculate Amount
                    paymentDetail.setAmount(Double.parseDouble(CalculateAmount.calculateAmount(user)));
                    paymentDetail.setName(user.getFullName());
                    paymentDetail.setProductInfo(Integer.toString(user.getUserId()));
                    paymentDetail.setPhone(String.valueOf(user.getMobileNumber()));
                    paymentDetail.setEmail("kushkumardhawan@gmail.com");

                    paymentDetail = PaymentUtil.populatePaymentDetail(paymentDetail);

                    //Save Data to Entity
                    UserTranactionEntity transactionEntity = new UserTranactionEntity();
                    transactionEntity.setUserId(user.getUserId());
                    transactionEntity.setEmail("kushkumardhawan@gmail.com");
                    transactionEntity.setName(user.getFullName());
                    transactionEntity.setPhone(String.valueOf(user.getMobileNumber()));
                    transactionEntity.setAmount(CalculateAmount.calculateAmount(user));
                    transactionEntity.setPaymentStatus("");
                    transactionEntity.setTransactionId(paymentDetail.getTxnId());
                    transactionEntity.setActive(true);
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    Date date = new Date(timestamp.getTime());
                    transactionEntity.setCreatedDate(date);
                    transactionEntity.setPaymentdateresponse(date);
                    userTransactionService.saveTransaction(transactionEntity);


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
                    model.addAttribute("user", user);
                    return "paymentpage";
                }

            } else {
                return "applicationform";
            }
        } catch (Exception ex) {
            return "applicationform";
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
                                  @RequestParam String bank_ref_num,
                                  @RequestParam String addedon,
                                  @RequestParam (value = "additionalCharges", required = false, defaultValue = "") String additionalCharges
                                 ) {
        PaymentCallback paymentCallback = new PaymentCallback();
        UserTranactionEntity entity_ = null;

        try {



            if (Utilities.empty(additionalCharges)) {
                paymentCallback.setAdditionalCharges("");
            } else {
                paymentCallback.setAdditionalCharges(additionalCharges);
            }

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


            if (Utilities.empty(mode.toString())) {

                paymentCallback.setMode(PaymentMode.NA);
            } else {
                paymentCallback.setMode(mode);
            }

             entity_ = userTransactionService.getUserTransaction(Integer.parseInt(productinfo));
            if(entity_!=null){
                //Check if the transaction ID is Same
                if(!entity_.getTransactionId().equalsIgnoreCase(txnid) && Utilities.empty(txnid)){
                    //Payment False
                    entity_.setPaymentStatus(status);
                    entity_.setBankRefNumber(bank_ref_num);
                    entity_.setPaymentMode(mode.toString());
                    entity_.setError("Transaction ID Null");
                    entity_.setMihpayId(mihpayid);
                    request.getSession().setAttribute("paymentStatus", "Failed");
                    request.getSession().setAttribute("TransactionId", entity_.getTransactionId());
                    request.getSession().setAttribute("Amount", entity_.getAmount());
                    request.getSession().setAttribute("ApplicationId", entity_.getUserId());
                    request.getSession().setAttribute("Name", entity_.getName());
                    request.getSession().setAttribute("MobileNumber", entity_.getPhone());
                    request.getSession().setAttribute("email", entity_.getEmail());
                    userTransactionService.saveTransaction(entity_);
                    return "paymentResponse";
                }else{
                    //Check Hash
                    if(hash.equalsIgnoreCase(PaymentUtil.verifyHash(paymentCallback))){
                       if(status.equalsIgnoreCase("success")){
                           entity_.setPaymentStatus("Success");
                           entity_.setBankRefNumber(bank_ref_num);
                           entity_.setPaymentMode(mode.toString());
                           entity_.setMihpayId(mihpayid);
                           entity_.setError("");
                           request.getSession().setAttribute("paymentStatus", "Success");
                           request.getSession().setAttribute("TransactionId", entity_.getTransactionId());
                           request.getSession().setAttribute("Amount", entity_.getAmount());
                           request.getSession().setAttribute("ApplicationId", entity_.getUserId());
                           request.getSession().setAttribute("Name", entity_.getName());
                           request.getSession().setAttribute("MobileNumber", entity_.getPhone());
                           request.getSession().setAttribute("email", entity_.getEmail());
                           //SEND OTP TO CLIENT
                           SMSServices sms = new SMSServices();
                           String SMSServerCode = null;
                           otp_response = sms.sendOtpSMS(Constants.smsUsername,
                                   Constants.smsPassword, Constants.otp_Message +" "+ entity_.getUserId() + Constants.otp_MessageEnd,
                                   Constants.smsSenderId,entity_.getPhone(),Constants.smsSecureKey, Constants.templateId);

                           if (!otp_response.isEmpty()) {
                               System.out.println(otp_response);
                               SMSServerCode = otp_response.split(",")[0];

                               if(SMSServerCode.equalsIgnoreCase("402")){
                                   OTPEntity otp = new OTPEntity();
                                   otp.setCdac_response(otp_response);
                                   otp.setPhone(String.valueOf(entity_.getPhone()));
                                   otp.setUserId(entity_.getUserId());
                                   otp.setSent_message(Constants.otp_Message +" "+ entity_.getUserId() + Constants.otp_MessageEnd);
                                   Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                   Date date = new Date(timestamp.getTime());
                                   otp.setSendTime(date);
                                   otpService.saveOTP(otp);

                               }else{
                                   //Save to DB no SMS
                                   OTPEntity otp = new OTPEntity();
                                   otp.setCdac_response(otp_response);
                                   otp.setPhone(String.valueOf(entity_.getPhone()));
                                   otp.setUserId(entity_.getUserId());
                                   otp.setSent_message(Constants.otp_Message +" "+ entity_.getUserId() + Constants.otp_MessageEnd);
                                   Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                   Date date = new Date(timestamp.getTime());
                                   otp.setSendTime(date);
                                   otpService.saveOTP(otp);
                               }

                           }else{
                               OTPEntity otp = new OTPEntity();
                               otp.setCdac_response("No Resonse From CDAC");
                               otp.setPhone(String.valueOf(entity_.getPhone()));
                               otp.setUserId(entity_.getUserId());
                               otp.setSent_message(Constants.otp_Message +" "+ entity_.getUserId() + Constants.otp_MessageEnd);
                               Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                               Date date = new Date(timestamp.getTime());
                               otp.setSendTime(date);
                               otpService.saveOTP(otp);
                           }




                           userTransactionService.saveTransaction(entity_);
                           return "paymentResponse";
                       }else{
                           entity_.setPaymentStatus("Faliure");
                           entity_.setBankRefNumber(bank_ref_num);
                           entity_.setPaymentMode(mode.toString());
                           entity_.setMihpayId(mihpayid);
                           entity_.setError("PAyment Failed . Please contact Admin.");
                           request.getSession().setAttribute("paymentStatus", "Failed");
                           request.getSession().setAttribute("TransactionId", entity_.getTransactionId());
                           request.getSession().setAttribute("Amount", entity_.getAmount());
                           request.getSession().setAttribute("ApplicationId", entity_.getUserId());
                           request.getSession().setAttribute("Name", entity_.getName());
                           request.getSession().setAttribute("MobileNumber", entity_.getPhone());
                           request.getSession().setAttribute("email", entity_.getEmail());
                           userTransactionService.saveTransaction(entity_);
                           return "paymentResponse";
                       }
                    }else{
                        entity_.setPaymentStatus("Faliure");
                        entity_.setBankRefNumber(bank_ref_num);
                        entity_.setPaymentMode(mode.toString());
                        entity_.setMihpayId(mihpayid);
                        entity_.setError("Hash Mismatched");
                        request.getSession().setAttribute("paymentStatus", "Failed, Hash Mismatched");
                        request.getSession().setAttribute("TransactionId", entity_.getTransactionId());
                        request.getSession().setAttribute("Amount", entity_.getAmount());
                        request.getSession().setAttribute("ApplicationId", entity_.getUserId());
                        request.getSession().setAttribute("Name", entity_.getName());
                        request.getSession().setAttribute("MobileNumber", entity_.getPhone());
                        request.getSession().setAttribute("email", entity_.getEmail());
                        userTransactionService.saveTransaction(entity_);
                        return "paymentResponse";
                    }

                }
            }else{
                entity_.setPaymentStatus("Faliure");
                entity_.setBankRefNumber(bank_ref_num);
                entity_.setPaymentMode(mode.toString());
                entity_.setMihpayId(mihpayid);
                entity_.setError("No data Found for the corresponding Application ID");
                request.getSession().setAttribute("paymentStatus", "Failed, Hash Mismatched");
                request.getSession().setAttribute("TransactionId", entity_.getTransactionId());
                request.getSession().setAttribute("Amount", entity_.getAmount());
                request.getSession().setAttribute("ApplicationId", entity_.getUserId());
                request.getSession().setAttribute("Name", entity_.getName());
                request.getSession().setAttribute("MobileNumber", entity_.getPhone());
                request.getSession().setAttribute("email", entity_.getEmail());
                userTransactionService.saveTransaction(entity_);
                return "paymentResponse";
            }



        } catch (Exception ex) {
            entity_.setPaymentStatus("Faliure");
            entity_.setBankRefNumber(bank_ref_num);
            entity_.setPaymentMode(mode.toString());
            entity_.setMihpayId(mihpayid);
            entity_.setError("Unexpected Error Occured!");
            request.getSession().setAttribute("paymentStatus", "Failed, Hash Mismatched");
            request.getSession().setAttribute("TransactionId", entity_.getTransactionId());
            request.getSession().setAttribute("Amount", entity_.getAmount());
            request.getSession().setAttribute("ApplicationId", entity_.getUserId());
            request.getSession().setAttribute("Name", entity_.getName());
            request.getSession().setAttribute("MobileNumber", entity_.getPhone());
            request.getSession().setAttribute("email", entity_.getEmail());
            userTransactionService.saveTransaction(entity_);
            return "paymentResponse";
        }


    }

    private UserTranactionEntity saveEntity(PaymentCallback paymentCallback){
        UserTranactionEntity entity = new UserTranactionEntity();
        entity.setActive(true);
        entity.setAmount(paymentCallback.getAmount());
        entity.setBankRefNumber(paymentCallback.getBank_ref_num());
        entity.setEmail(paymentCallback.getEmail());
        entity.setError(paymentCallback.getError());
        entity.setMihpayId(paymentCallback.getMihpayid());
        entity.setName(paymentCallback.getFirstname());
        entity.setPaymentMode(paymentCallback.getMode().toString());
        entity.setPaymentStatus("Failed");
        entity.setTransactionId(paymentCallback.getTxnid());
        entity.setUserId(Integer.parseInt(paymentCallback.getProductinfo()));
        entity.setPhone(paymentCallback.getPhone());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        entity.setCreatedDate(date);
        UserTranactionEntity transactionDB = userTransactionService.saveTransaction(entity);
        return transactionDB;
    }

    private UserTranactionEntity saveEntitySuccess(PaymentCallback paymentCallback){
        UserTranactionEntity entity = new UserTranactionEntity();
        entity.setActive(true);
        entity.setAmount(paymentCallback.getAmount());
        entity.setBankRefNumber(paymentCallback.getBank_ref_num());
        entity.setEmail(paymentCallback.getEmail());
        entity.setError(paymentCallback.getError());
        entity.setMihpayId(paymentCallback.getMihpayid());
        entity.setName(paymentCallback.getFirstname());
        entity.setPaymentMode(paymentCallback.getMode().toString());
        entity.setPaymentStatus("success");
        entity.setTransactionId(paymentCallback.getTxnid());
        entity.setUserId(Integer.parseInt(paymentCallback.getProductinfo()));
        entity.setPhone(paymentCallback.getPhone());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());
        entity.setCreatedDate(date);
        UserTranactionEntity transactionDB = userTransactionService.saveTransaction(entity);
        return transactionDB;
    }


}