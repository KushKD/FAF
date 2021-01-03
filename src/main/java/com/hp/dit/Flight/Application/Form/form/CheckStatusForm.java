package com.hp.dit.Flight.Application.Form.form;

import java.io.Serializable;

public class CheckStatusForm implements Serializable {

    private String applicationId;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }


    @Override
    public String toString() {
        return "CheckStatusForm{" +
                "applicationId='" + applicationId + '\'' +
                '}';
    }
}
