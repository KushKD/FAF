package com.hp.dit.Flight.Application.Form.form;

import com.hp.dit.Flight.Application.Form.modal.AvailedServices;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class FlightApplicationForm implements Serializable {


   // private String captchaCode, captchaCorrect, captchaIncorrect;

    private String category;
    private String registrationType;
    private String relationPrifix;
    private String fullName;
    private String relationName;
    private String mobileNumber;
    private String age;
    private String weight;
    private String luggageWeight;
    private String permanentAddress;
    private String correspondenceAddress;
    private String reasonAvailingFlightService;
    public String comments;
    private String tentitiveFlightDate;
    private String availedFlightBefore15;
    private String earlierFlightServiceEmergency;
    private String declerationUser;
    private String dateTravel;
    private String halipadLocation;
    private String halipadDistrict;
    private String flightDistrictToGoFrom;
    private String flightHelipadNameToGoFrom;
    private List<AvailedServices>  availedServiceListForm;
    private String earlierService;

    private MultipartFile aadhaar_doc;
    private MultipartFile officeCardDoc;
    private MultipartFile medicalDoc;
    private MultipartFile otherDoc;


    public String getEarlierService() {
        return earlierService;
    }

    public void setEarlierService(String earlierService) {
        this.earlierService = earlierService;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public MultipartFile getAadhaar_doc() {
        return aadhaar_doc;
    }

    public void setAadhaar_doc(MultipartFile aadhaar_doc) {
        this.aadhaar_doc = aadhaar_doc;
    }

    public MultipartFile getOfficeCardDoc() {
        return officeCardDoc;
    }

    public void setOfficeCardDoc(MultipartFile officeCardDoc) {
        this.officeCardDoc = officeCardDoc;
    }

    public MultipartFile getMedicalDoc() {
        return medicalDoc;
    }

    public void setMedicalDoc(MultipartFile medicalDoc) {
        this.medicalDoc = medicalDoc;
    }

    public MultipartFile getOtherDoc() {
        return otherDoc;
    }

    public void setOtherDoc(MultipartFile otherDoc) {
        this.otherDoc = otherDoc;
    }

    public List<AvailedServices> getAvailedServiceListForm() {
        return availedServiceListForm;
    }

    public void setAvailedServiceListForm(List<AvailedServices> availedServiceListForm) {
        this.availedServiceListForm = availedServiceListForm;
    }

    public String getAvailedFlightBefore15() {
        return availedFlightBefore15;
    }

    public void setAvailedFlightBefore15(String availedFlightBefore15) {
        this.availedFlightBefore15 = availedFlightBefore15;
    }

    public String getEarlierFlightServiceEmergency() {
        return earlierFlightServiceEmergency;
    }

    public void setEarlierFlightServiceEmergency(String earlierFlightServiceEmergency) {
        this.earlierFlightServiceEmergency = earlierFlightServiceEmergency;
    }

    public String getDeclerationUser() {
        return declerationUser;
    }

    public void setDeclerationUser(String declerationUser) {
        this.declerationUser = declerationUser;
    }

    public String getFlightDistrictToGoFrom() {
        return flightDistrictToGoFrom;
    }

    public void setFlightDistrictToGoFrom(String flightDistrictToGoFrom) {
        this.flightDistrictToGoFrom = flightDistrictToGoFrom;
    }

    public String getFlightHelipadNameToGoFrom() {
        return flightHelipadNameToGoFrom;
    }

    public void setFlightHelipadNameToGoFrom(String flightHelipadNameToGoFrom) {
        this.flightHelipadNameToGoFrom = flightHelipadNameToGoFrom;
    }

    public String getDateTravel() {
        return dateTravel;
    }

    public void setDateTravel(String dateTravel) {
        this.dateTravel = dateTravel;
    }

    public String getHalipadLocation() {
        return halipadLocation;
    }

    public void setHalipadLocation(String halipadLocation) {
        this.halipadLocation = halipadLocation;
    }

    public String getHalipadDistrict() {
        return halipadDistrict;
    }

    public void setHalipadDistrict(String halipadDistrict) {
        this.halipadDistrict = halipadDistrict;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getRelationPrifix() {
        return relationPrifix;
    }

    public void setRelationPrifix(String relationPrifix) {
        this.relationPrifix = relationPrifix;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(String luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getReasonAvailingFlightService() {
        return reasonAvailingFlightService;
    }

    public void setReasonAvailingFlightService(String reasonAvailingFlightService) {
        this.reasonAvailingFlightService = reasonAvailingFlightService;
    }

    public String getTentitiveFlightDate() {
        return tentitiveFlightDate;
    }

    public void setTentitiveFlightDate(String tentitiveFlightDate) {
        this.tentitiveFlightDate = tentitiveFlightDate;
    }


//    public String getCaptchaCode() {
//        return captchaCode;
//    }
//
//    public void setCaptchaCode(String captchaCode) {
//        this.captchaCode = captchaCode;
//    }
//
//    public String getCaptchaCorrect() {
//        return captchaCorrect;
//    }
//
//    public void setCaptchaCorrect(String captchaCorrect) {
//        this.captchaCorrect = captchaCorrect;
//    }
//
//    public String getCaptchaIncorrect() {
//        return captchaIncorrect;
//    }
//
//    public void setCaptchaIncorrect(String captchaIncorrect) {
//        this.captchaIncorrect = captchaIncorrect;
//    }

    @Override
    public String toString() {
        return "FlightApplicationForm{" +
                "category='" + category + '\'' +
                ", registrationType='" + registrationType + '\'' +
                ", relationPrifix='" + relationPrifix + '\'' +
                ", fullName='" + fullName + '\'' +
                ", relationName='" + relationName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", age='" + age + '\'' +
                ", weight='" + weight + '\'' +
                ", luggageWeight='" + luggageWeight + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", correspondenceAddress='" + correspondenceAddress + '\'' +
                ", reasonAvailingFlightService='" + reasonAvailingFlightService + '\'' +
                ", comments='" + comments + '\'' +
                ", tentitiveFlightDate='" + tentitiveFlightDate + '\'' +
                ", availedFlightBefore15='" + availedFlightBefore15 + '\'' +
                ", earlierFlightServiceEmergency='" + earlierFlightServiceEmergency + '\'' +
                ", declerationUser='" + declerationUser + '\'' +
                ", dateTravel='" + dateTravel + '\'' +
                ", halipadLocation='" + halipadLocation + '\'' +
                ", halipadDistrict='" + halipadDistrict + '\'' +
                ", flightDistrictToGoFrom='" + flightDistrictToGoFrom + '\'' +
                ", flightHelipadNameToGoFrom='" + flightHelipadNameToGoFrom + '\'' +
                ", availedServiceListForm=" + availedServiceListForm +
                ", earlierService='" + earlierService + '\'' +
                ", aadhaar_doc=" + aadhaar_doc +
                ", officeCardDoc=" + officeCardDoc +
                ", medicalDoc=" + medicalDoc +
                ", otherDoc=" + otherDoc +
                '}';
    }
}
