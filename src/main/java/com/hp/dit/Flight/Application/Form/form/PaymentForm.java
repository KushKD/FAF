package com.hp.dit.Flight.Application.Form.form;

import java.io.Serializable;

public class PaymentForm implements Serializable {

    private String name;
    private String phone;
    private String user_id_transaction_id;    //application_user_id
    private Double amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_id_transaction_id() {
        return user_id_transaction_id;
    }

    public void setUser_id_transaction_id(String user_id_transaction_id) {
        this.user_id_transaction_id = user_id_transaction_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentForm{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", user_id_transaction_id='" + user_id_transaction_id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
