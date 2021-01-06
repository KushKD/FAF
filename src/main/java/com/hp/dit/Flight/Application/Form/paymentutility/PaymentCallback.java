package com.hp.dit.Flight.Application.Form.paymentutility;

import java.io.Serializable;

public class PaymentCallback implements Serializable {

    private String txnid;
    private String mihpayid;
   // private PaymentMode mode;
    private String status;
    private String hash;

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
                ", status='" + status + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
