package com.hp.dit.Flight.Application.Form.form;

import java.io.Serializable;

public class ViewApplications implements Serializable {

    private String location;
    private String helipadName;
    private String Date;
    private String appliactionStatus;


    public String getAppliactionStatus() {
        return appliactionStatus;
    }

    public void setAppliactionStatus(String appliactionStatus) {
        this.appliactionStatus = appliactionStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHelipadName() {
        return helipadName;
    }

    public void setHelipadName(String helipadName) {
        this.helipadName = helipadName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "ViewApplications{" +
                "location='" + location + '\'' +
                ", helipadName='" + helipadName + '\'' +
                ", Date='" + Date + '\'' +
                ", appliactionStatus='" + appliactionStatus + '\'' +
                '}';
    }
}
