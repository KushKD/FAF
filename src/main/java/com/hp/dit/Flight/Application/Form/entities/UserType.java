package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_typeuser")
public class UserType implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_typeuser_user_type_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_typeuser_user_type_id_seq", sequenceName = "public.mst_typeuser_user_type_id_seq", initialValue = 1, allocationSize = 1)

    @Column(name="user_type_id")
    private Integer userTypeId;

    @Column(name="user_type_name")
    private String userTypeName;

    @Column(name="active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
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
        return "UserType{" +
                "userTypeId=" + userTypeId +
                ", userTypeName='" + userTypeName + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}
