package com.hp.dit.Flight.Application.Form.projections;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.math.BigInteger;

public class FormDataListProjection implements Serializable {

    Integer userId;
    String fullName;
    BigInteger mobileNumber;
    String applicationStatus;
    String paymentStatus;

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigInteger getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(BigInteger mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }


    @Override
    public String toString() {
        return "FormDataListProjection{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", applicationStatus='" + applicationStatus + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
