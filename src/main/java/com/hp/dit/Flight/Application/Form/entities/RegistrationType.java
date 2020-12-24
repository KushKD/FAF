package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_reservationtype")
public class RegistrationType implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_reservationtype_reservationtype_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_reservationtype_reservationtype_id_seq", sequenceName = "public.mst_reservationtype_reservationtype_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="reservationtype_id")
    private Integer reservationTypeId;

    @Column(name="reservationtype_name")
    private String reservationTypeName;

    @Column(name="active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    public Integer getReservationTypeId() {
        return reservationTypeId;
    }

    public void setReservationTypeId(Integer reservationTypeId) {
        this.reservationTypeId = reservationTypeId;
    }

    public String getReservationTypeName() {
        return reservationTypeName;
    }

    public void setReservationTypeName(String reservationTypeName) {
        this.reservationTypeName = reservationTypeName;
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
        return "RegistrationType{" +
                "reservationTypeId=" + reservationTypeId +
                ", reservationTypeName='" + reservationTypeName + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}
