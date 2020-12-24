package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_helipad")
public class Helipad implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_helipad_helipad_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_helipad_helipad_id_seq", sequenceName = "public.mst_helipad_helipad_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "helipad_id")
    private Integer helipadId;

    @Column(name = "helipad_name")
    private String helipadName;

    @Column(name = "district_id")
    private Integer districtId;


    @Column(name = "active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;


    public Integer getHelipadId() {
        return helipadId;
    }

    public void setHelipadId(Integer helipadId) {
        this.helipadId = helipadId;
    }

    public String getHelipadName() {
        return helipadName;
    }

    public void setHelipadName(String helipadName) {
        this.helipadName = helipadName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
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
        return "Helipad{" +
                "helipadId=" + helipadId +
                ", helipadName='" + helipadName + '\'' +
                ", districtId=" + districtId +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}