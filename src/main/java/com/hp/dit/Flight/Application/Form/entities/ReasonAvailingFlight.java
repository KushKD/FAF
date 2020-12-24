package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_reasonavailing_flight")
public class ReasonAvailingFlight implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_reasonavailing_flight_reasonavailing_flight_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_reasonavailing_flight_reasonavailing_flight_id_seq", sequenceName = "public.mst_reasonavailing_flight_reasonavailing_flight_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="reasonavailing_flight_id")
    private Integer reasonAvailingFlightId;

    @Column(name="reasonavailing_flight_name")
    private String reasonAvailingFlightName;

    @Column(name="active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    public Integer getReasonAvailingFlightId() {
        return reasonAvailingFlightId;
    }

    public void setReasonAvailingFlightId(Integer reasonAvailingFlightId) {
        this.reasonAvailingFlightId = reasonAvailingFlightId;
    }

    public String getReasonAvailingFlightName() {
        return reasonAvailingFlightName;
    }

    public void setReasonAvailingFlightName(String reasonAvailingFlightName) {
        this.reasonAvailingFlightName = reasonAvailingFlightName;
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
        return "ReasonAvailingFlightService{" +
                "reasonAvailingFlightId=" + reasonAvailingFlightId +
                ", reasonAvailingFlightName='" + reasonAvailingFlightName + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}
