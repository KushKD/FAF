package com.hp.dit.Flight.Application.Form.validators;


import com.hp.dit.Flight.Application.Form.CustomLogin.CustomUserService;
import com.hp.dit.Flight.Application.Form.form.RolesForm;
import com.hp.dit.Flight.Application.Form.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
    @Autowired
    private CustomUserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RolesForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RolesForm user = (RolesForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "NotEmpty");
        if (user.getRoleName().length() <= 3 || user.getRoleName().length() > 32) {
            errors.rejectValue("roleName", "Size.rolesForm.roleName");
        }
        if (roleService.checkRoleName(user.getRoleName()) != null) {
            errors.rejectValue("roleName", "Duplicate.rolesForm.roleName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleDescription", "NotEmpty");
        if (user.getRoleDescription().length() <= 3 || user.getRoleDescription().length() >40 ) {
            errors.rejectValue("roleDescription", "Size.rolesForm.roleDescription");
        }




    }
}
