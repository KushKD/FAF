package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_district_dest")
public class DistrictDest implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_district_dest_district_id_dest_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_district_dest_district_id_dest_seq", sequenceName = "public.mst_district_dest_district_id_dest_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "district_id_dest")
    private Integer districtId;

    @Column(name = "district_name_dest")
    private String districtName;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
        return "DistrictDest{" +
                "districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}