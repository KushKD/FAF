package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user_transaction")
public class UserTranactionEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "user_transaction_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_transaction_id_seq", sequenceName = "public.user_transaction_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private String amount;

    @Column(name = "payment_Status")
    private String paymentStatus;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "mihpay_id")
    private String mihpayId;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "bank_ref_number")
    private String bankRefNumber;

    @Column(name = "error")
    private String error;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMihpayId() {
        return mihpayId;
    }

    public void setMihpayId(String mihpayId) {
        this.mihpayId = mihpayId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBankRefNumber() {
        return bankRefNumber;
    }

    public void setBankRefNumber(String bankRefNumber) {
        this.bankRefNumber = bankRefNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "UserTranactionEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", mihpayId='" + mihpayId + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", bankRefNumber='" + bankRefNumber + '\'' +
                ", error='" + error + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}
