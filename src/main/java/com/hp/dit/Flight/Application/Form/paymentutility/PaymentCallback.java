package com.hp.dit.Flight.Application.Form.paymentutility;

import com.hp.dit.Flight.Application.Form.enums.PaymentMode;

import java.io.Serializable;

public class PaymentCallback implements Serializable {

    private String txnid;
    private String mihpayid;
    private PaymentMode mode;
    private String status;
    private String hash;
    private String key;
    private String amount;
    private String productinfo;
    private String firstname;
    private String email;
    private String phone;
    private String error;
    private String bank_ref_num;
    private String additionalCharges;
    private String addedon;

    public String getAddedon() {
        return addedon;
    }

    public void setAddedon(String addedon) {
        this.addedon = addedon;
    }

    public String getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(String additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getBank_ref_num() {
        return bank_ref_num;
    }

    public void setBank_ref_num(String bank_ref_num) {
        this.bank_ref_num = bank_ref_num;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public void setMode(PaymentMode mode) {
        this.mode = mode;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getMihpayid() {
        return mihpayid;
    }

    public void setMihpayid(String mihpayid) {
        this.mihpayid = mihpayid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "PaymentCallback{" +
                "txnid='" + txnid + '\'' +
                ", mihpayid='" + mihpayid + '\'' +
                ", mode=" + mode +
                ", status='" + status + '\'' +
                ", hash='" + hash + '\'' +
                ", key='" + key + '\'' +
                ", amount='" + amount + '\'' +
                ", productinfo='" + productinfo + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", error='" + error + '\'' +
                ", bank_ref_num='" + bank_ref_num + '\'' +
                ", additionalCharges='" + additionalCharges + '\'' +
                ", addedon='" + addedon + '\'' +
                '}';
    }
}
