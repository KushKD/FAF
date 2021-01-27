package com.hp.dit.Flight.Application.Form.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="otp_logs")
public class OTPEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "otp_logs_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "otp_logs_id_seq", sequenceName = "public.otp_logs_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sent_message")
    private String sent_message;

    @Column(name = "cdac_response")
    private String cdac_response;

    @Column(name = "send_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSent_message() {
        return sent_message;
    }

    public void setSent_message(String sent_message) {
        this.sent_message = sent_message;
    }

    public String getCdac_response() {
        return cdac_response;
    }

    public void setCdac_response(String cdac_response) {
        this.cdac_response = cdac_response;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "OTPEntity{" +
                "helipadId=" + id +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", sent_message='" + sent_message + '\'' +
                ", cdac_response='" + cdac_response + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
