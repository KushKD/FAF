package com.hp.dit.Flight.Application.Form.modal;

import java.io.Serializable;
import java.math.BigInteger;

public class RolesModal implements Serializable {

    private BigInteger role_id;
    private String role_name;

    public BigInteger getRole_id() {
        return role_id;
    }

    public void setRole_id(BigInteger role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }



    @Override
    public String toString() {
        return "RolesModal{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
