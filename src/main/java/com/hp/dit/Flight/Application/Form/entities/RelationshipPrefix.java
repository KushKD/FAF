package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_relationshipprefix")
public class RelationshipPrefix {

    @Id
    @GeneratedValue(generator = "mst_relationshipprefix_relationshipprifix_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_relationshipprefix_relationshipprifix_id_seq", sequenceName = "public.mst_relationshipprefix_relationshipprifix_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="relationshipprifix_id")
    private Integer relationshipPrifixId;

    @Column(name="relationshipprifix_name")
    private String relationshipPrifixName;

    @Column(name="active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    public Integer getRelationshipPrifixId() {
        return relationshipPrifixId;
    }

    public void setRelationshipPrifixId(Integer relationshipPrifixId) {
        this.relationshipPrifixId = relationshipPrifixId;
    }

    public String getRelationshipPrifixName() {
        return relationshipPrifixName;
    }

    public void setRelationshipPrifixName(String relationshipPrifixName) {
        this.relationshipPrifixName = relationshipPrifixName;
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
        return "RelationshipPrefix{" +
                "relationshipPrifixId=" + relationshipPrifixId +
                ", relationshipPrifixName='" + relationshipPrifixName + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                '}';
    }
}
