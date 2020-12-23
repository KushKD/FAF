package com.hp.dit.Flight.Application.Form.form;

import java.io.Serializable;

public class FlightApplicationForm implements Serializable {

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
    private String tentitiveFlightDate;
//    private String availedFlightBefore15;
//    private String earlierFlightServiceEmergency;
//    private String declerationUser;


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
                ", tentitiveFlightDate='" + tentitiveFlightDate + '\'' +
                '}';
    }
}
