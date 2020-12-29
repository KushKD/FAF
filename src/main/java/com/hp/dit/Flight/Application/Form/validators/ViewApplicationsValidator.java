package com.hp.dit.Flight.Application.Form.validators;

import com.hp.dit.Flight.Application.Form.form.ViewApplications;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ViewApplicationsValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return ViewApplications.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ViewApplications idcard = (ViewApplications) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        if (idcard.getDate()== null || idcard.getDate().isEmpty()) {
            errors.rejectValue("date", "Size.viewApplications.date");
        }

        if(idcard.getLocation().equalsIgnoreCase("0") || idcard.getLocation().isEmpty() || idcard.getLocation()==null){
            errors.rejectValue("location", "Select.viewApplications.location");
        }

        if(idcard.getHelipadName().equalsIgnoreCase("0") || idcard.getHelipadName().isEmpty() || idcard.getHelipadName()==null){
            errors.rejectValue("helipadName", "Select.viewApplications.helipadName");
        }

    }
}
