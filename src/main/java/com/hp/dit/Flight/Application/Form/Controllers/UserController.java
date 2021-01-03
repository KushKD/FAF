package com.hp.dit.Flight.Application.Form.Controllers;

import com.hp.dit.Flight.Application.Form.entities.RolesEntity;
import com.hp.dit.Flight.Application.Form.entities.UserEntity;
import com.hp.dit.Flight.Application.Form.form.RegisterUser;
import com.hp.dit.Flight.Application.Form.services.RoleService;
import com.hp.dit.Flight.Application.Form.services.UserService;
import com.hp.dit.Flight.Application.Form.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userservice;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserValidator userValidator;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("registerUser", new RegisterUser());
        return "createuser";
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

}
