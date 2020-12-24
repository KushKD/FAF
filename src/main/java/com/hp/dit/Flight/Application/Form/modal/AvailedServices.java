package com.hp.dit.Flight.Application.Form.modal;

import java.io.Serializable;

public class AvailedServices implements Serializable {

    private String dateTravelled;
    private String helipadDistrict;
    private String helipadName;

    public String getDateTravelled() {
        return dateTravelled;
    }

    public void setDateTravelled(String dateTravelled) {
        this.dateTravelled = dateTravelled;
    }

    public String getHelipadDistrict() {
        return helipadDistrict;
    }

    public void setHelipadDistrict(String helipadDistrict) {
        this.helipadDistrict = helipadDistrict;
    }

    public String getHelipadName() {
        return helipadName;
    }

    public void setHelipadName(String helipadName) {
        this.helipadName = helipadName;
    }

    @Override
    public String toString() {
        return "AvailedServices{" +
                "dateTravelled='" + dateTravelled + '\'' +
                ", helipadDistrict='" + helipadDistrict + '\'' +
                ", helipadName='" + helipadName + '\'' +
                '}';
    }
}
