package com.hp.dit.Flight.Application.Form.entities;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="userformdata_previousservice")
public class userFormDataPreviousServiceEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "userformdata_previousservice_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "userformdata_previousservice_id_seq", sequenceName = "public.userformdata_previousservice_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id")
    private Integer id;


    @Column(name="date")
    private String date;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="district_id")
    private Integer districtId;

    @Column(name="helipad_id")
    private Integer helipadId;

    @Column(name="active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

//    @ManyToOne(optional = false,targetEntity = FlightFormEntity.class,  cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", insertable = true)
//    private FlightFormEntity userformdata;

//    public FlightFormEntity getUserformdata() {
//        return userformdata;
//    }
//
//    public void setUserformdata(FlightFormEntity userformdata) {
//        this.userformdata = userformdata;


//    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getHelipadId() {
        return helipadId;
    }

    public void setHelipadId(Integer helipadId) {
        this.helipadId = helipadId;
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
        return "userFormDataPreviousServiceEntity{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", userId=" + userId +
                ", districtId=" + districtId +
                ", helipadId=" + helipadId +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}
