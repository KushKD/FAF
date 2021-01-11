package com.hp.dit.Flight.Application.Form.form;

import java.io.Serializable;

public class CheckPayment  implements Serializable {

    private String application_id;

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    @Override
    public String toString() {
        return "CheckPayment{" +
                "application_id='" + application_id + '\'' +
                '}';
    }
}
