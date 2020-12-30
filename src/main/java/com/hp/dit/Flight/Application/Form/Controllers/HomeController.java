package com.hp.dit.Flight.Application.Form.Controllers;


import com.hp.dit.Flight.Application.Form.entities.*;
import com.hp.dit.Flight.Application.Form.form.*;
import com.hp.dit.Flight.Application.Form.projections.FormDataListProjection;
import com.hp.dit.Flight.Application.Form.services.*;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import com.hp.dit.Flight.Application.Form.utilities.DateUtilities;
import com.hp.dit.Flight.Application.Form.validators.FlightFormValidator;
import com.hp.dit.Flight.Application.Form.validators.RoleValidator;
import com.hp.dit.Flight.Application.Form.validators.UserValidator;
import com.hp.dit.Flight.Application.Form.validators.ViewApplicationsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
//import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private FlightFormValidator flightFormValidator;

    @Autowired
    private FlightFormService flightFormService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private userFormDataPreviousService userFormDataPreviousServices;


    @Autowired
    private UserService userservice;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ViewApplicationsValidator viewApplicationsValidator;


    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainpage(Model model) {
        return "mainpage";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String homePage(Model model) {

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


    @RequestMapping(value = "/applicationform", method = RequestMethod.GET)
    public String index(Model model,HttpServletRequest request) {
        request.getSession().setAttribute("successMessage", "");
        model.addAttribute("flightApplicationForm", new FlightApplicationForm());
        return "flightapplication";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("registerUser", new RegisterUser());
        return "createuser";
    }


    @RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
    public String checkStatus(Model model) {
        model.addAttribute("registerUser", new RegisterUser());
        return "checkStatus";
    }


    @RequestMapping(value = "/paymentpage", method = RequestMethod.GET)
    public String paymentpage(Model model) {
        //model.addAttribute("registerUser", new RegisterUser());
        return "paymentpage";
    }


    @RequestMapping(value = "/saveDetails", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String saveDetails(@ModelAttribute("flightApplicationForm") FlightApplicationForm flightApplicationForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        flightFormValidator.validate(flightApplicationForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "flightapplication";
        }
        try {
            FlightFormEntity data = new FlightFormEntity();
            data = populateFlightForm(flightApplicationForm);
            if (data != null) {
                FlightFormEntity savedData = flightFormService.saveUser(data);
                //Setting the table
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

                request.getSession().setAttribute("successMessage", savedData.getFullName() + "  Successfully Saved. ID is:- " + savedData.getUserId());
                return "paymentpage";
            } else {
                request.getSession().setAttribute("successMessage", "Unable to Save the Data. Please try again");
                return "flightapplication";
            }
        } catch (Exception ex) {
            model.addAttribute("serverError", ex.toString());
            return "flightapplication";
        }

    }

    private FlightFormEntity populateFlightForm(FlightApplicationForm flightApplicationForm) {

        FlightFormEntity flightForm = new FlightFormEntity();
        UserType userType = new UserType();
        RegistrationType reservationType = new RegistrationType();
        ReasonAvailingFlight reason_availing_flight = new ReasonAvailingFlight();
        RelationshipPrefix prefix = new RelationshipPrefix();
        District district = new District();
        Helipad helipad = new Helipad();

        try {

            flightForm.setActive(true);

            userType.setUserTypeId(Integer.parseInt(flightApplicationForm.getCategory()));
            flightForm.setCategory(userType);

            reservationType.setReservationTypeId(Integer.parseInt(flightApplicationForm.getRegistrationType()));
            flightForm.setRegistrationType(reservationType);

            prefix.setRelationshipPrifixId(Integer.parseInt(flightApplicationForm.getRelationPrifix()));
            flightForm.setRelationPrifix(prefix);
            flightForm.setFullName(flightApplicationForm.getFullName());
            flightForm.setRelationName(flightApplicationForm.getRelationName());
            flightForm.setMobileNumber(Long.parseLong(flightApplicationForm.getMobileNumber()));
            flightForm.setAge(Integer.parseInt(flightApplicationForm.getAge()));
            flightForm.setWeight(Integer.parseInt(flightApplicationForm.getWeight()));
            flightForm.setLuggageWeight(Integer.parseInt(flightApplicationForm.getLuggageWeight()));
            flightForm.setCorrespondenceAddress(flightApplicationForm.getCorrespondenceAddress());
            flightForm.setPermanentAddress(flightApplicationForm.getPermanentAddress());

            reason_availing_flight.setReasonAvailingFlightId(Integer.parseInt(flightApplicationForm.getReasonAvailingFlightService()));
            flightForm.setReasonAvailingFlightService(reason_availing_flight);
            flightForm.setTentitiveFlightDate(flightApplicationForm.getTentitiveFlightDate());

            district.setDistrictId(Integer.parseInt(flightApplicationForm.getFlightDistrictToGoFrom()));
            flightForm.setFlightDistrictToGoFrom(district);

            helipad.setHelipadId(Integer.parseInt(flightApplicationForm.getFlightHelipadNameToGoFrom()));
            flightForm.setFlightHelipadNameToGoFrom(helipad);
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
                fileName = DateUtilities.getDateTime() + "__" + fileName;
                flightForm.setAadhaar_doc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getAadhaar_doc(), fileName);
            } else {
                flightForm.setAadhaar_doc("");
            }

            if (!flightApplicationForm.getMedicalDoc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(flightApplicationForm.getMedicalDoc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = DateUtilities.getDateTime() + "__" + fileName;
                flightForm.setMedicalDoc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getMedicalDoc(), fileName);
            } else {
                flightForm.setMedicalDoc("");
            }

            if (!flightApplicationForm.getOtherDoc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(flightApplicationForm.getOtherDoc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = DateUtilities.getDateTime() + "__" + fileName;
                flightForm.setMedicalDoc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getOtherDoc(), fileName);
            } else {
                flightForm.setOtherDoc("");
            }

            if (!flightApplicationForm.getOfficeCardDoc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(flightApplicationForm.getOfficeCardDoc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = DateUtilities.getDateTime() + "__" + fileName;
                flightForm.setMedicalDoc(fileName);
                fileStorageService.storeFile(flightApplicationForm.getOfficeCardDoc(), fileName);
            } else {
                flightForm.setOfficeCardDoc("");
            }


            Optional<RolesEntity> role = Optional.ofNullable(roleService.checkRoleName("Admin"));
            //if (role.get() != null) {
            flightForm.setApplicationForwardedToRole(role.get());


        } catch (Exception ex) {
            flightForm = null;
        }

        return flightForm;

    }


    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    @Transactional
    public String saveUser(@ModelAttribute("registerUser") RegisterUser registerUser, BindingResult bindingResult, Model model, HttpServletRequest request) {
        userValidator.validate(registerUser, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createuser";
        }
        try {
            UserEntity user = new UserEntity();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setActive(true);
            user.setIs_deleted(false);
            user.setMobileNumber(Long.valueOf(registerUser.getMobileNumber()));
            user.setUsername(registerUser.getUsername());
            user.setPassword(encoder.encode(registerUser.getPassword()));
            String roleIid = registerUser.getRoleId();
            user.setEmail(registerUser.getEmailAddress());
            user.setGender(registerUser.getGender());

            Optional<RolesEntity> role = roleService.getRoleDetails(roleIid);
            if (role.get() != null) {
                List<RolesEntity> list = new ArrayList<RolesEntity>();
                list.add(role.get());
                user.setRoles(list);
                UserEntity savedData = userservice.saveUser(user);

                request.getSession().setAttribute("successMessage", savedData.getUsername() + "  Successfully Saved. ID is" + savedData.getUserId());
                registerUser.setMobileNumber("");
                registerUser.setPasswordConfirm("");
                registerUser.setPassword("");
                registerUser.setUsername("");
                registerUser.setRoleId("0");
                return "createuser";
            } else {
                registerUser.setMobileNumber("");
                registerUser.setPasswordConfirm("");
                registerUser.setPassword("");
                registerUser.setUsername("");
                registerUser.setRoleId("0");
                model.addAttribute("serverError", "No Role Name and Role Description Exist with this ID");
                return "createuser";
            }

        } catch (Exception ex) {
            registerUser.setMobileNumber("");
            registerUser.setPasswordConfirm("");
            registerUser.setUsername("");
            registerUser.setPassword("");
            model.addAttribute("serverError", ex.toString());
            return "createuser";
        }

    }


    @RequestMapping(value = "/createRole", method = RequestMethod.GET)
    public String createRole(Model model) {
        model.addAttribute("rolesForm", new RolesForm());
        return "createrole";
    }


    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("rolesForm") RolesForm roleForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        roleValidator.validate(roleForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createrole";
        }
        try {
            RolesEntity rolesEntity = new RolesEntity();
            rolesEntity.setActive(true);
            rolesEntity.setRoleName(roleForm.getRoleName());
            rolesEntity.setRoleDescription(roleForm.getRoleDescription());
            RolesEntity savedData = roleService.saveRole(rolesEntity);
            roleForm.setRoleName("");
            roleForm.setRoleDescription("");
            request.getSession().setAttribute("successMessage", savedData.getRoleName() + " role Successfully Saved. ID is" + savedData.getRoleId());
            return "createrole";
        } catch (Exception ex) {
            roleForm.setRoleName("");
            roleForm.setRoleDescription("");
            model.addAttribute("serverError", ex.toString());
            return "createrole";
        }
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public String showIdCardList(Model model) {
        model.addAttribute("viewApplications", new ViewApplications());
        return "applications";
    }




    @RequestMapping(value = "/filterApplications", method = RequestMethod.POST)
    public String getIdCardList(@ModelAttribute("viewApplications") ViewApplications applications, BindingResult bindingResult, Model model, HttpServletRequest request) {
        viewApplicationsValidator.validate(applications, bindingResult);

        if (bindingResult.hasErrors()) {
            return "applications";
        }
        try {
            List<Object[]> data = flightFormService.getProjectionApplicationList(Integer.parseInt(applications.getLocation()),
                    Integer.parseInt(applications.getHelipadName()), applications.getDate().trim(),Constants.PENDING);
            if (!data.isEmpty()) {

                List<FormDataListProjection> projectionData = new ArrayList<>();


                for (Object[] result : data) {
                    FormDataListProjection pojo = new FormDataListProjection();
                    pojo.setUserId((Integer) result[0]);
                    pojo.setFullName((String) result[1]);
                    pojo.setMobileNumber((BigInteger) result[2]);
                    pojo.setApplicationStatus((String) result[3]);
                    projectionData.add(pojo);
                }



                request.getSession().setAttribute("successMessage", "Data found Successfully");
                model.addAttribute("applications", projectionData);
                model.addAttribute("helipadName", applications.getHelipadName());
                model.addAttribute("location", applications.getLocation());
                applications.setDate(applications.getDate());
                applications.setHelipadName(applications.getHelipadName());
                applications.setLocation(applications.getLocation());
                return "applications";
            } else {
                model.addAttribute("helipadName", applications.getHelipadName());
                model.addAttribute("location", applications.getLocation());
                applications.setDate(applications.getDate());
                applications.setHelipadName(applications.getHelipadName());
                applications.setLocation(applications.getLocation());
                model.addAttribute("serverError", "No Data available for the current District and Barrier");
                return "applications";
            }


        } catch (Exception ex) {
            applications.setDate("");
            applications.setHelipadName("");
            applications.setLocation("");
            model.addAttribute("serverError", ex.toString());
            return "applications";
        }



    }

    //getUserDetails

    @RequestMapping(value = "/getUserDetails/{id}", method=RequestMethod.GET)
    public String getUserDetailComplete(@PathVariable("id")int id,Model model,HttpServletRequest request) {


        model.addAttribute("actionForm", new ActionForm());
        FlightFormEntity user = new FlightFormEntity();
        try {
            user = flightFormService.getDataByUserID(id);
            if (user != null) {
                System.out.println(user.toString());
                model.addAttribute("userdata", user);
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


    @RequestMapping(value = "/updateApplication", method = RequestMethod.POST)
    public String update_application(@ModelAttribute("actionForm") ActionForm actionForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
       // roleValidator.validate(roleForm, bindingResult);
        System.out.println(actionForm.toString());

//        if (bindingResult.hasErrors()) {
//            return "createrole";
//        }
        try {
            FlightFormEntity user = new FlightFormEntity();
            user = flightFormService.getCompleteApplication(Integer.parseInt(actionForm.getUser_id()));
            if(user!=null){
              if(actionForm.getAction().equalsIgnoreCase("A")){
                  user.setApplicaionStatus(Constants.APPROVED);
              }else if(actionForm.getAction().equalsIgnoreCase("R")){
                  user.setApplicaionStatus(Constants.REJECTED);
              }else{
                  user.setApplicaionStatus(Constants.PENDING);
              }
              user.setComments(actionForm.getComments());
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
