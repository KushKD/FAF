package com.hp.dit.Flight.Application.Form.validators;

import com.hp.dit.Flight.Application.Form.form.CheckStatusForm;
import com.hp.dit.Flight.Application.Form.form.ViewApplications;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CheckStatusValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return  CheckStatusValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        CheckStatusForm checkStatus = (CheckStatusForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applicationId", "NotEmpty");
        if (checkStatus.getApplicationId()== null || checkStatus.getApplicationId().isEmpty()) {
            errors.rejectValue("applicationId", "Size.checkStatus.appId");
        }

    }
}
