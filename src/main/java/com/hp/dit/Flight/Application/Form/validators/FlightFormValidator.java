package com.hp.dit.Flight.Application.Form.validators;

import com.hp.dit.Flight.Application.Form.form.FlightApplicationForm;
import com.hp.dit.Flight.Application.Form.form.RolesForm;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FlightFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return FlightApplicationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FlightApplicationForm flightForm = (FlightApplicationForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");
        if (flightForm.getCategory().equalsIgnoreCase("0")) {
            errors.rejectValue("category", "Size.flightApplicationForm.category");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "registrationType", "NotEmpty");
        if (flightForm.getRegistrationType().equalsIgnoreCase("0")) {
            errors.rejectValue("registrationType", "Size.flightApplicationForm.registrationType");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "relationPrifix", "NotEmpty");
        if (flightForm.getRelationPrifix().equalsIgnoreCase("0")) {
            errors.rejectValue("relationPrifix", "Size.flightApplicationForm.relationPrifix");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotEmpty");
        if (flightForm.getFullName().length() <= 3 || flightForm.getFullName().length() > 32) {
            errors.rejectValue("fullName", "Size.flightApplicationForm.fullName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "relationName", "NotEmpty");
        if (flightForm.getRelationName().length() <= 3 || flightForm.getRelationName().length() > 32) {
            errors.rejectValue("relationName", "Size.flightApplicationForm.relationName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "NotEmpty");
        if (flightForm.getMobileNumber().length() != 10 ) {
            errors.rejectValue("mobileNumber", "Size.flightApplicationForm.mobileNumber");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty");
        if (flightForm.getAge().length() > 3 ) {
            errors.rejectValue("age", "Size.flightApplicationForm.age");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "NotEmpty");
        if (flightForm.getWeight().length() > 5) {
            errors.rejectValue("weight", "Size.flightApplicationForm.weight");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luggageWeight", "NotEmpty");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "permanentAddress", "NotEmpty");




        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reasonAvailingFlightService", "NotEmpty");
        if (flightForm.getReasonAvailingFlightService().equalsIgnoreCase("0")) {
            errors.rejectValue("reasonAvailingFlightService", "Size.reasonAvailingFlightService.reasonAvailingFlightService");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tentitiveFlightDate", "NotEmpty");
        if (flightForm.getTentitiveFlightDate().length() <= 3 || flightForm.getRelationName().length() > 32) {
            errors.rejectValue("tentitiveFlightDate", "Size.flightApplicationForm.tentitiveFlightDate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "availedFlightBefore15", "NotEmpty");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "earlierFlightServiceEmergency", "NotEmpty");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "declerationUser", "NotEmpty");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightDistrictToGoFrom", "NotEmpty");
        if (flightForm.getFlightDistrictToGoFrom().equalsIgnoreCase("0")) {
            errors.rejectValue("flightDistrictToGoFrom", "Size.flightApplicationForm.flightDistrictToGoFrom");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightHelipadNameToGoFrom", "NotEmpty");
        if (flightForm.getRelationName().equalsIgnoreCase("0")) {
            errors.rejectValue("flightHelipadNameToGoFrom", "Size.flightApplicationForm.flightHelipadNameToGoFrom");
        }


        if(flightForm.getAadhaar_doc().getSize()!=0){

            if(getFileSizeMb(flightForm.getAadhaar_doc().getSize())>5){
                errors.rejectValue("aadhaar_doc", "Size.flightApplicationForm.aadhaar_doc");
            }
        }

        if(flightForm.getMedicalDoc().getSize()!=0){

            if(getFileSizeMb(flightForm.getMedicalDoc().getSize())>5){
                errors.rejectValue("medicalDoc", "Size.flightApplicationForm.medicalDoc");
            }
        }

        if(flightForm.getOfficeCardDoc().getSize()!=0){

            if(getFileSizeMb(flightForm.getOfficeCardDoc().getSize())>5){
                errors.rejectValue("officeCardDoc", "Size.flightApplicationForm.officeCardDoc");
            }
        }

        if(flightForm.getOtherDoc().getSize()!=0){

            if(getFileSizeMb(flightForm.getOtherDoc().getSize())>5){
                errors.rejectValue("otherDoc", "Size.flightApplicationForm.otherDoc");
            }
        }





    }

    private long getFileSizeMb(long size){
        long fileSizeInKB = size / 1024;
        long fileSizeInMB = fileSizeInKB / 1024;
        return fileSizeInMB;
    }
}
