package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.*;
import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;
import com.hp.dit.Flight.Application.Form.services.FileStorageService;
import com.hp.dit.Flight.Application.Form.services.FlightFormService;
import com.hp.dit.Flight.Application.Form.services.RoleService;
import com.hp.dit.Flight.Application.Form.services.userFormDataPreviousService;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import com.hp.dit.Flight.Application.Form.utilities.DateUtilities;
import com.hp.dit.Flight.Application.Form.validators.FlightFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationFormContoller {


    @Autowired
    private FlightFormValidator flightFormValidator;

    @Autowired
    private FlightFormService flightFormService;

    @Autowired
    private userFormDataPreviousService userFormDataPreviousServices;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RoleService roleService;


    private static final Logger logger = LoggerFactory.getLogger(ApplicationFormContoller.class);

    @RequestMapping(value = "/applicationform", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("successMessage", "");
        model.addAttribute("flightApplicationForm", new FlightApplicationForm());
        return "flightapplication";
    }

    @RequestMapping(value = "/saveDetails", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String saveDetails(@ModelAttribute("flightApplicationForm") FlightApplicationForm flightApplicationForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {

        String captcha=(String)session.getAttribute("CAPTCHA");
        if(captcha==null || (captcha!=null && !captcha.equals(flightApplicationForm.getCaptcha()))){
            flightApplicationForm.setCaptcha("");
            model.addAttribute("serverError", "Captcha Mismatch");
            return "flightapplication";
        }else {
            flightFormValidator.validate(flightApplicationForm, bindingResult);
            if (bindingResult.hasErrors()) {
                return "flightapplication";
            }
            try {
                FlightFormEntity data = new FlightFormEntity();
                data = populateFlightForm(flightApplicationForm);
                if (data != null) {

                    try {
                        FlightFormEntity savedData = flightFormService.saveUser(data);
                        if (!flightApplicationForm.getAvailedServiceListForm().isEmpty()) {
                            //Check if there is value or not inside the list
                            List<userFormDataPreviousServiceEntity> availedServices = new ArrayList<>();
                            userFormDataPreviousServiceEntity datax = null;
                            District district = null;
                            Helipad helipad = null;
                            for (int i = 0; i < flightApplicationForm.getAvailedServiceListForm().size(); i++) {
                                datax = new userFormDataPreviousServiceEntity();
                                district = new District();
                                helipad = new Helipad();

                                if (!flightApplicationForm.getAvailedServiceListForm().get(i).getDateTravelled().equalsIgnoreCase("")
                                        && flightApplicationForm.getAvailedServiceListForm().get(i).getDateTravelled() != null
                                        && !flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadDistrict().equalsIgnoreCase("0")
                                        && !flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadName().equalsIgnoreCase("0")) {

                                    datax.setDate(flightApplicationForm.getAvailedServiceListForm().get(i).getDateTravelled());
                                    district.setDistrictId(Integer.parseInt(flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadDistrict()));
                                    datax.setDistrictId(district);
                                    helipad.setHelipadId(Integer.parseInt(flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadName()));
                                    datax.setHelipadId(helipad);
                                    datax.setUserId(savedData.getUserId());
                                    datax.setActive(true);
                                    availedServices.add(datax);

                                }
                            }
                            userFormDataPreviousServices.saveData(availedServices);

                        }
                        //Get User Data
                        //request.getSession().setAttribute("successMessage", savedUSer.getFullName() + "  Successfully Saved. ID is:- " + savedUSer.getUserId());
                        // model.addAttribute("user", savedUSer);
                        redirectAttributes.addFlashAttribute("userID", savedData.getUserId());
                        return "redirect:/paymentpage";
                    } catch (Exception ex) {
                        request.getSession().setAttribute("serverError", ex.getLocalizedMessage().toString());
                        return "paymentpage";
                    }


                } else {
                    request.getSession().setAttribute("successMessage", "Unable to Save the Data. Please try again");
                    return "flightapplication";
                }
            } catch (Exception ex) {
                model.addAttribute("serverError", ex.toString());
                return "flightapplication";
            }
        }

    }

    private FlightFormEntity populateFlightForm(FlightApplicationForm flightApplicationForm) {

        logger.info("Inside Populate Function");

        FlightFormEntity flightForm = new FlightFormEntity();
        UserType userType = new UserType();
        RegistrationType reservationType = new RegistrationType();
        ReasonAvailingFlight reason_availing_flight = new ReasonAvailingFlight();
        RelationshipPrefix prefix = new RelationshipPrefix();
        District district = new District();
        DistrictDest district_dest = new DistrictDest();
        Helipad helipad = new Helipad();
        HelipadDest helipad_dest = new HelipadDest();

        try {

            flightForm.setActive(true);

            userType.setUserTypeId(Integer.parseInt(flightApplicationForm.getCategory()));
            flightForm.setCategory(userType);
            logger.info(userType.toString());

            reservationType.setReservationTypeId(Integer.parseInt(flightApplicationForm.getRegistrationType()));
            flightForm.setRegistrationType(reservationType);
            logger.info(reservationType.toString());

            prefix.setRelationshipPrifixId(Integer.parseInt(flightApplicationForm.getRelationPrifix()));
            flightForm.setRelationPrifix(prefix);
            logger.info(prefix.toString());
            flightForm.setFullName(flightApplicationForm.getFullName());
            logger.info(flightForm.getFullName().toString());
            flightForm.setRelationName(flightApplicationForm.getRelationName());
            logger.info(flightForm.getRelationName().toString());
            flightForm.setMobileNumber(Long.parseLong(flightApplicationForm.getMobileNumber()));
            logger.info(flightForm.getMobileNumber().toString());
            flightForm.setAge(Integer.parseInt(flightApplicationForm.getAge()));
            flightForm.setWeight(Integer.parseInt(flightApplicationForm.getWeight()));
            flightForm.setLuggageWeight(Integer.parseInt(flightApplicationForm.getLuggageWeight()));
            flightForm.setCorrespondenceAddress(flightApplicationForm.getCorrespondenceAddress());
            flightForm.setPermanentAddress(flightApplicationForm.getPermanentAddress());
            logger.info(flightForm.getPermanentAddress().toString());

            reason_availing_flight.setReasonAvailingFlightId(Integer.parseInt(flightApplicationForm.getReasonAvailingFlightService()));
            flightForm.setReasonAvailingFlightService(reason_availing_flight);
            logger.info(reason_availing_flight.toString());
            flightForm.setTentitiveFlightDate(DateUtilities.convertDate(flightApplicationForm.getTentitiveFlightDate()));
            logger.info(DateUtilities.convertDate(flightApplicationForm.getTentitiveFlightDate()));

            flightForm.setTentitiveFlightDateTo(DateUtilities.convertDate(flightApplicationForm.getServiceavailingDateto()));
            logger.info(DateUtilities.convertDate(flightApplicationForm.getServiceavailingDateto()));

            district.setDistrictId(Integer.parseInt(flightApplicationForm.getFlightDistrictToGoFrom()));
            flightForm.setFlightDistrictToGoFrom(district);
            logger.info(district.toString());

            helipad.setHelipadId(Integer.parseInt(flightApplicationForm.getFlightHelipadNameToGoFrom()));
            flightForm.setFlightHelipadNameToGoFrom(helipad);
            logger.info(helipad.toString());

            district_dest.setDistrictId(Integer.parseInt(flightApplicationForm.getFlightDistrictToGoFrom_dest()));
            flightForm.setDistrict_id_dest(district_dest);
            logger.info(district_dest.toString());

            helipad_dest.setHelipadId(Integer.parseInt(flightApplicationForm.getFlightHelipadNameToGoFrom_dest()));
            flightForm.setHelipad_id_dest(helipad_dest);
            logger.info(helipad_dest.toString());


            flightForm.setAvailedFlightBefore15(flightApplicationForm.getAvailedFlightBefore15());
            flightForm.setEarlierFlightServiceEmergency(flightApplicationForm.getEarlierFlightServiceEmergency());
            flightForm.setDeclerationUser(flightApplicationForm.getDeclerationUser());
            flightForm.setEarlierService(flightApplicationForm.getEarlierService());
            flightForm.setComments(flightApplicationForm.getComments());
            flightForm.setApplicaionStatus(Constants.PENDING);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            flightForm.setCreatedDate(date);

            if (!flightApplicationForm.getAadhaar_doc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(flightApplicationForm.getAadhaar_doc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                flightForm.setAadhaar_doc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getAadhaar_doc(), fileName);
            } else {
                flightForm.setAadhaar_doc("");
            }

            if (!flightApplicationForm.getMedicalDoc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(flightApplicationForm.getMedicalDoc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                flightForm.setMedicalDoc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getMedicalDoc(), fileName);
            } else {
                flightForm.setMedicalDoc("");
            }

            if (!flightApplicationForm.getOtherDoc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(flightApplicationForm.getOtherDoc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                flightForm.setMedicalDoc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getOtherDoc(), fileName);
            } else {
                flightForm.setOtherDoc("");
            }

            if (!flightApplicationForm.getOfficeCardDoc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(flightApplicationForm.getOfficeCardDoc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                flightForm.setMedicalDoc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getOfficeCardDoc(), fileName);
            } else {
                flightForm.setOfficeCardDoc("");
            }


            Optional<RolesEntity> role = Optional.ofNullable(roleService.checkRoleName("Admin"));
            //if (role.get() != null) {
            flightForm.setApplicationForwardedToRole(role.get());
            logger.info(role.get().toString());


        } catch (Exception ex) {
            flightForm = null;
        }

        return flightForm;

    }
}
