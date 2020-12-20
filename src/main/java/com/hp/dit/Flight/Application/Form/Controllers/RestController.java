package com.hp.dit.Flight.Application.Form.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

@org.springframework.web.bind.annotation.RestController
public class RestController {

//    @Autowired
//    private UserValidator userValidator;
//
//    @Autowired
//    private RoleValidator roleValidator;
//
//    @Autowired
//    private GenerateIdCardValidator generateIdCardValidator;
//
//    @Autowired
//    private CustomUserService userService;
//
//    @Autowired
//    private UserService userservice;
//
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private SecurityService securityService;
//
//    @Autowired
//    private VehicleOwnerEntriesService vehicleOwnerEntriesService;
//
//    @Autowired
//    private SearchIdCardValidator searchIdCardValidator;
//
//    @Autowired
//    private VahanLogsRepository vahanLogsRepository;

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);




    //flight_application_form.
        @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public String index(Model model) {
            System.out.println("We are Here");
            ResourceBundle bundle
                    = ResourceBundle.getBundle("lang/messages", Locale.forLanguageTag("hi-In"));
            String message1 = bundle.getString("instructions.head.one");
            String message2= bundle.getString("instructions.head.two");
            String message3 = bundle.getString("instructions.head.three");
            String message4 = bundle.getString("instructions.head.four");
            String message5= bundle.getString("instructions.head.five");
            System.out.println(message1);
            System.out.println(message2);
            System.out.println(message3);
            System.out.println(message4);
            System.out.println(message5);
            return message1;
    }


//    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
//    public String createUser(Model model) {
//        model.addAttribute("registerUser", new RegisterUser());
//        return "createuser";
//    }

//    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
//    @Transactional
//    public String saveUser(@ModelAttribute("registerUser") RegisterUser registerUser, BindingResult bindingResult, Model model, HttpServletRequest request) {
//        userValidator.validate(registerUser, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "createuser";
//        }
//        try {
//            UserEntity user = new UserEntity();
//            PasswordEncoder encoder = new BCryptPasswordEncoder();
//            user.setActive(true);
//            user.setMobileNumber(Long.valueOf(registerUser.getMobileNumber()));
//            user.setUserName(registerUser.getUsername());
//            user.setPassword(encoder.encode(registerUser.getPassword()));
//            String roleIid = registerUser.getRoleId();
//
//            Optional<RolesEntity> role = roleService.getRoleDetails(roleIid);
//            if (role.get() != null) {
//                List<RolesEntity> list = new ArrayList<RolesEntity>();
//                list.add(role.get());
//                user.setRoles(list);
//                UserEntity savedData = userservice.saveUser(user);
//
//                request.getSession().setAttribute("successMessage", savedData.getUserName() + "  Successfully Saved. ID is" + savedData.getUserId());
//                registerUser.setMobileNumber("");
//                registerUser.setPasswordConfirm("");
//                registerUser.setPassword("");
//                registerUser.setUsername("");
//                registerUser.setRoleId("0");
//                return "createuser";
//            } else {
//                registerUser.setMobileNumber("");
//                registerUser.setPasswordConfirm("");
//                registerUser.setPassword("");
//                registerUser.setUsername("");
//                registerUser.setRoleId("0");
//                model.addAttribute("serverError", "No Role Name and Role Description Exist with this ID");
//                return "createuser";
//            }
//
//        } catch (Exception ex) {
//            registerUser.setMobileNumber("");
//            registerUser.setPasswordConfirm("");
//            registerUser.setUsername("");
//            registerUser.setPassword("");
//            model.addAttribute("serverError", ex.toString());
//            return "createuser";
//        }
//
//    }


//    @RequestMapping(value = "/createRole", method = RequestMethod.GET)
//    public String createRole(Model model) {
//        model.addAttribute("rolesForm", new RolesForm());
//        return "createrole";
//    }
//
//
//    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
//    public String saveRole(@ModelAttribute("rolesForm") RolesForm roleForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
//        roleValidator.validate(roleForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "createrole";
//        }
//        try {
//            RolesEntity rolesEntity = new RolesEntity();
//            rolesEntity.setActive(true);
//            rolesEntity.setRoleName(roleForm.getRoleName());
//            rolesEntity.setRoleDescription(roleForm.getRoleDescription());
//            RolesEntity savedData = roleService.saveRole(rolesEntity);
//            roleForm.setRoleName("");
//            roleForm.setRoleDescription("");
//            request.getSession().setAttribute("successMessage", savedData.getRoleName() + " role Successfully Saved. ID is" + savedData.getRoleId());
//            return "createrole";
//        } catch (Exception ex) {
//            roleForm.setRoleName("");
//            roleForm.setRoleDescription("");
//            model.addAttribute("serverError", ex.toString());
//            return "createrole";
//        }
//    }

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
