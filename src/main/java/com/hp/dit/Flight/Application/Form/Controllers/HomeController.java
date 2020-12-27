package com.hp.dit.Flight.Application.Form.Controllers;


import com.hp.dit.Flight.Application.Form.entities.FlightFormEntity;
import com.hp.dit.Flight.Application.Form.entities.RolesEntity;
import com.hp.dit.Flight.Application.Form.entities.UserEntity;
import com.hp.dit.Flight.Application.Form.entities.userFormDataPreviousServiceEntity;
import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;
import com.hp.dit.Flight.Application.Form.form.RegisterUser;
import com.hp.dit.Flight.Application.Form.form.RolesForm;
import com.hp.dit.Flight.Application.Form.modal.AvailedServices;
import com.hp.dit.Flight.Application.Form.services.*;
import com.hp.dit.Flight.Application.Form.utilities.Constants;
import com.hp.dit.Flight.Application.Form.utilities.DateUtilities;
import com.hp.dit.Flight.Application.Form.validators.FlightFormValidator;
import com.hp.dit.Flight.Application.Form.validators.RoleValidator;
import com.hp.dit.Flight.Application.Form.validators.UserValidator;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sun.tools.javac.comp.Todo;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
//import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
                    for (int i = 0; i < flightApplicationForm.getAvailedServiceListForm().size(); i++) {
                        datax = new userFormDataPreviousServiceEntity();
                        if (!flightApplicationForm.getAvailedServiceListForm().get(i).getDateTravelled().equalsIgnoreCase("")
                                && flightApplicationForm.getAvailedServiceListForm().get(i).getDateTravelled() != null
                                && !flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadDistrict().equalsIgnoreCase("0")
                                && !flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadName().equalsIgnoreCase("0")) {

                            datax.setDate(flightApplicationForm.getAvailedServiceListForm().get(i).getDateTravelled());
                            datax.setDistrictId(Integer.parseInt(flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadDistrict()));
                            datax.setHelipadId(Integer.parseInt(flightApplicationForm.getAvailedServiceListForm().get(i).getHelipadName()));
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

        try {
            flightForm.setActive(true);
            flightForm.setCategory(Integer.parseInt(flightApplicationForm.getCategory()));
            flightForm.setRegistrationType(Integer.parseInt(flightApplicationForm.getRegistrationType()));
            flightForm.setRelationPrifix(Integer.parseInt(flightApplicationForm.getRelationPrifix()));
            flightForm.setFullName(flightApplicationForm.getFullName());
            flightForm.setRelationName(flightApplicationForm.getRelationName());
            flightForm.setMobileNumber(Long.parseLong(flightApplicationForm.getMobileNumber()));
            flightForm.setAge(Integer.parseInt(flightApplicationForm.getAge()));
            flightForm.setWeight(Integer.parseInt(flightApplicationForm.getWeight()));
            flightForm.setLuggageWeight(Integer.parseInt(flightApplicationForm.getLuggageWeight()));
            flightForm.setCorrespondenceAddress(flightApplicationForm.getCorrespondenceAddress());
            flightForm.setPermanentAddress(flightApplicationForm.getPermanentAddress());
            flightForm.setReasonAvailingFlightService(Integer.parseInt(flightApplicationForm.getReasonAvailingFlightService()));
            flightForm.setTentitiveFlightDate(flightApplicationForm.getTentitiveFlightDate());
            flightForm.setFlightDistrictToGoFrom(Integer.parseInt(flightApplicationForm.getFlightDistrictToGoFrom()));
            flightForm.setFlightHelipadNameToGoFrom(Integer.parseInt(flightApplicationForm.getFlightHelipadNameToGoFrom()));
            flightForm.setAvailedFlightBefore15(flightApplicationForm.getAvailedFlightBefore15());
            flightForm.setEarlierFlightServiceEmergency(flightApplicationForm.getEarlierFlightServiceEmergency());
            flightForm.setDeclerationUser(flightApplicationForm.getDeclerationUser());
            flightForm.setEarlierService(flightApplicationForm.getEarlierService());
            flightForm.setComments(flightApplicationForm.getComments());
            flightForm.setApplicaionStatus(Constants.PENDING);

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
            flightForm.setApplicationForwardedToRole(Math.toIntExact(role.get().getRoleId()));


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

//    @RequestMapping(value = "/showIdCards", method = RequestMethod.GET)
//    public String showIdCardList(Model model) {
//        model.addAttribute("showIdCardList", new showIdCardList());
//        return "showidcards";
//    }
//
//    @RequestMapping(value = "/searchId", method = RequestMethod.GET)
//    public String searchIdCard(Model model) {
//        model.addAttribute("searchId", new SearchID());
//        return "searchid";
//    }

//    @RequestMapping(value = "/getIdCardsSearch", method = RequestMethod.POST)
//    public String getIdCardListSearch(@ModelAttribute("searchId") SearchID idcard, BindingResult bindingResult, Model model, HttpServletRequest request) {
//        searchIdCardValidator.validate(idcard, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "searchid";
//        }
//        try {
//            List<VehicleOwnerEntries> data = vehicleOwnerEntriesService.searchIdentityList(Long.valueOf(idcard.getMobileNumber()), idcard.getVehicleNumber());
//
//            if (!data.isEmpty()) {
//                request.getSession().setAttribute("successMessage", "Data found Successfully");
//                model.addAttribute("vehicledata", data);
//                idcard.setMobileNumber(idcard.getMobileNumber());
//                idcard.setVehicleNumber(idcard.getVehicleNumber());
//                return "searchid";
//            } else {
//                idcard.setMobileNumber("");
//                idcard.setVehicleNumber("");
//                model.addAttribute("serverError", "No Data available for the current Vehicle Number and Mobile Number");
//                return "searchid";
//            }
//
//
//        } catch (Exception ex) {
//            idcard.setMobileNumber("");
//            idcard.setVehicleNumber("");
//            model.addAttribute("serverError", ex.toString());
//            return "searchid";
//        }
//
//    }


//    @RequestMapping(value = "/getIdCards", method = RequestMethod.POST)
//    public String getIdCardList(@ModelAttribute("showIdCardList") showIdCardList idcard, BindingResult bindingResult, Model model, HttpServletRequest request) {
//        generateIdCardValidator.validate(idcard, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "showidcards";
//        }
//        try {
//            List<VehicleOwnerEntries> data = vehicleOwnerEntriesService.getDataViaDistrictBarrier(Integer.parseInt(idcard.getDistrict_id()),
//                    Integer.parseInt(idcard.getBarrier_id()), idcard.getDate().trim());
//            if (!data.isEmpty()) {
//                request.getSession().setAttribute("successMessage", "Data found Successfully");
//                model.addAttribute("vehicledata", data);
//                model.addAttribute("barrierid", idcard.getBarrier_id());
//                model.addAttribute("districtid", idcard.getDistrict_id());
//                idcard.setDate(idcard.getDate());
//                idcard.setBarrier_id(idcard.getBarrier_id());
//                idcard.setDistrict_id(idcard.getDistrict_id());
//                return "showidcards";
//            } else {
//                model.addAttribute("barrierid", idcard.getBarrier_id());
//                model.addAttribute("districtid", idcard.getDistrict_id());
//                idcard.setDate(idcard.getDate());
//                idcard.setBarrier_id(idcard.getBarrier_id());
//                idcard.setDistrict_id(idcard.getDistrict_id());
//                model.addAttribute("serverError", "No Data available for the current District and Barrier");
//                return "showidcards";
//            }
//
//
//        } catch (Exception ex) {
//            idcard.setDate("");
//            idcard.setBarrier_id("0");
//            idcard.setDistrict_id("0");
//            model.addAttribute("serverError", ex.toString());
//            return "showidcards";
//        }
//
//    }


//    @RequestMapping(value = "/generateId/{id}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
//    public @ResponseBody
//    ResponseEntity<InputStreamResource> printId(@PathVariable("id") String id) throws IOException, WriterException, DocumentException {
//
//        Optional<VehicleOwnerEntries> vehicleOwnerEntries = vehicleOwnerEntriesService.getOwnerDetails(Long.valueOf(id));
//        ByteArrayInputStream bis = GeneratePdfReport.generateIdCard(vehicleOwnerEntries.get());
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=" + vehicleOwnerEntries.get().getIdCardNumber() + ".pdf");
//
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//
//    }

//    @RequestMapping(value = "/getVahanLogs", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public void getVahanLogs(Model model, HttpServletResponse response, HttpServletRequest request) {
//
//        try {
//
//            List<VahanLog> dataForReports = null;
//            dataForReports = new ArrayList<>();
//            dataForReports = (List<VahanLog>) vahanLogsRepository.findAll();
//
//
//            if (!dataForReports.isEmpty()) {
//                response.setContentType("application/octet-stream");
//                response.setHeader("Content-Disposition", "attachment; filename=logs.xlsx");
//                ByteArrayInputStream stream = ExcelFileExporter.getLogsExcel(dataForReports);
//                IOUtils.copy(stream, response.getOutputStream());
//                response.flushBuffer();
//
//            } else {
//
//                response.setContentType("application/octet-stream");
//                response.setHeader("Content-Disposition", "attachment; filename=Report_id_card.xlsx");
//                ByteArrayInputStream stream = ExcelFileExporter.getLogsExcel(dataForReports);
//                IOUtils.copy(stream, response.getOutputStream());
//                response.flushBuffer();
//            }
//
//
//        } catch (Exception ex) {
//        }
//
//    }


}
