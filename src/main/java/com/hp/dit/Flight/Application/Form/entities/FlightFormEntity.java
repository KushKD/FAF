package com.hp.dit.Flight.Application.Form.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="userformdata")
public class FlightFormEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "users_user_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "public.users_user_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "user_id")
    private Integer userId;


    @OneToOne
    @JoinColumn(name="user_type_id")
    private UserType category;

    @OneToOne
    @JoinColumn(name="reservationtype_id" )
    private RegistrationType registrationType;

    @OneToOne
    @JoinColumn(name="relationshipprifix_id" )
    private RelationshipPrefix relationPrifix;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "relation_name")
    private String relationName;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "luggage_weight")
    private Integer luggageWeight;

    @Column(name = "p_address")
    private String permanentAddress;

    @Column(name = "c_address")
    private String correspondenceAddress;

    @OneToOne
    @JoinColumn(name = "reasonavailing_flight_id" )
    private ReasonAvailingFlight reasonAvailingFlightService;

    @Column(name = "comments")
    public String comments;

    @Column(name = "tentitive_flight_date")
    private String tentitiveFlightDate;

    @Column(name = "tentitive_flight_date_to")
    private String tentitiveFlightDateTo;

    @Column(name = "availed_flight_befor15")
    private String availedFlightBefore15;

    @Column(name = "earlier_flight_service_emergency")
    private String earlierFlightServiceEmergency;

    @Column(name = "decleration_user")
    private String declerationUser;


    @OneToOne
    @JoinColumn(name="district_id")
    private District flightDistrictToGoFrom;

    @OneToOne
    @JoinColumn(name="helipad_id")
    private Helipad flightHelipadNameToGoFrom;



    @OneToOne
    @JoinColumn(name="district_id_dest")
    private DistrictDest district_id_dest;

    @OneToOne
    @JoinColumn(name="helipad_id_dest")
    private HelipadDest helipad_id_dest;


    @Column(name = "earlierservice")
    private String earlierService;

    @Column(name = "aadhaar_doc_name")
    private String aadhaar_doc;

    @Column(name = "office_card_coc_name")
    private String officeCardDoc;

    @Column(name = "medical_doc_name")
    private String medicalDoc;

    @Column(name = "other_doc")
    private String otherDoc;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;


    @OneToOne
    @JoinColumn(name="role_id")
    private RolesEntity applicationForwardedToRole;



    @Column(name = "application_status")
    private String applicaionStatus;

    @Column(name = "concerned_authority_comments")
    private String concernedAuthorityComments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity = userFormDataPreviousServiceEntity.class)
    @JoinColumn(name="user_id")
    private List<userFormDataPreviousServiceEntity> userFormDataPreviousServiceEntities;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserType getCategory() {
        return category;
    }

    public void setCategory(UserType category) {
        this.category = category;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public RelationshipPrefix getRelationPrifix() {
        return relationPrifix;
    }

    public void setRelationPrifix(RelationshipPrefix relationPrifix) {
        this.relationPrifix = relationPrifix;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(Integer luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public ReasonAvailingFlight getReasonAvailingFlightService() {
        return reasonAvailingFlightService;
    }

    public void setReasonAvailingFlightService(ReasonAvailingFlight reasonAvailingFlightService) {
        this.reasonAvailingFlightService = reasonAvailingFlightService;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTentitiveFlightDate() {
        return tentitiveFlightDate;
    }

    public void setTentitiveFlightDate(String tentitiveFlightDate) {
        this.tentitiveFlightDate = tentitiveFlightDate;
    }

    public String getTentitiveFlightDateTo() {
        return tentitiveFlightDateTo;
    }

    public void setTentitiveFlightDateTo(String tentitiveFlightDateTo) {
        this.tentitiveFlightDateTo = tentitiveFlightDateTo;
    }

    public String getAvailedFlightBefore15() {
        return availedFlightBefore15;
    }

    public void setAvailedFlightBefore15(String availedFlightBefore15) {
        this.availedFlightBefore15 = availedFlightBefore15;
    }

    public String getEarlierFlightServiceEmergency() {
        return earlierFlightServiceEmergency;
    }

    public void setEarlierFlightServiceEmergency(String earlierFlightServiceEmergency) {
        this.earlierFlightServiceEmergency = earlierFlightServiceEmergency;
    }

    public String getDeclerationUser() {
        return declerationUser;
    }

    public void setDeclerationUser(String declerationUser) {
        this.declerationUser = declerationUser;
    }

    public District getFlightDistrictToGoFrom() {
        return flightDistrictToGoFrom;
    }

    public void setFlightDistrictToGoFrom(District flightDistrictToGoFrom) {
        this.flightDistrictToGoFrom = flightDistrictToGoFrom;
    }

    public Helipad getFlightHelipadNameToGoFrom() {
        return flightHelipadNameToGoFrom;
    }

    public void setFlightHelipadNameToGoFrom(Helipad flightHelipadNameToGoFrom) {
        this.flightHelipadNameToGoFrom = flightHelipadNameToGoFrom;
    }

    public DistrictDest getDistrict_id_dest() {
        return district_id_dest;
    }

    public void setDistrict_id_dest(DistrictDest district_id_dest) {
        this.district_id_dest = district_id_dest;
    }

    public HelipadDest getHelipad_id_dest() {
        return helipad_id_dest;
    }

    public void setHelipad_id_dest(HelipadDest helipad_id_dest) {
        this.helipad_id_dest = helipad_id_dest;
    }

    public String getEarlierService() {
        return earlierService;
    }

    public void setEarlierService(String earlierService) {
        this.earlierService = earlierService;
    }

    public String getAadhaar_doc() {
        return aadhaar_doc;
    }

    public void setAadhaar_doc(String aadhaar_doc) {
        this.aadhaar_doc = aadhaar_doc;
    }

    public String getOfficeCardDoc() {
        return officeCardDoc;
    }

    public void setOfficeCardDoc(String officeCardDoc) {
        this.officeCardDoc = officeCardDoc;
    }

    public String getMedicalDoc() {
        return medicalDoc;
    }

    public void setMedicalDoc(String medicalDoc) {
        this.medicalDoc = medicalDoc;
    }

    public String getOtherDoc() {
        return otherDoc;
    }

    public void setOtherDoc(String otherDoc) {
        this.otherDoc = otherDoc;
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

    public RolesEntity getApplicationForwardedToRole() {
        return applicationForwardedToRole;
    }

    public void setApplicationForwardedToRole(RolesEntity applicationForwardedToRole) {
        this.applicationForwardedToRole = applicationForwardedToRole;
    }

    public String getApplicaionStatus() {
        return applicaionStatus;
    }

    public void setApplicaionStatus(String applicaionStatus) {
        this.applicaionStatus = applicaionStatus;
    }

    public String getConcernedAuthorityComments() {
        return concernedAuthorityComments;
    }

    public void setConcernedAuthorityComments(String concernedAuthorityComments) {
        this.concernedAuthorityComments = concernedAuthorityComments;
    }

    public List<userFormDataPreviousServiceEntity> getUserFormDataPreviousServiceEntities() {
        return userFormDataPreviousServiceEntities;
    }

    public void setUserFormDataPreviousServiceEntities(List<userFormDataPreviousServiceEntity> userFormDataPreviousServiceEntities) {
        this.userFormDataPreviousServiceEntities = userFormDataPreviousServiceEntities;
    }

    @Override
    public String toString() {
        return "FlightFormEntity{" +
                "userId=" + userId +
                ", category=" + category +
                ", registrationType=" + registrationType +
                ", relationPrifix=" + relationPrifix +
                ", fullName='" + fullName + '\'' +
                ", relationName='" + relationName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", age=" + age +
                ", weight=" + weight +
                ", luggageWeight=" + luggageWeight +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", correspondenceAddress='" + correspondenceAddress + '\'' +
                ", reasonAvailingFlightService=" + reasonAvailingFlightService +
                ", comments='" + comments + '\'' +
                ", tentitiveFlightDate='" + tentitiveFlightDate + '\'' +
                ", tentitiveFlightDateTo='" + tentitiveFlightDateTo + '\'' +
                ", availedFlightBefore15='" + availedFlightBefore15 + '\'' +
                ", earlierFlightServiceEmergency='" + earlierFlightServiceEmergency + '\'' +
                ", declerationUser='" + declerationUser + '\'' +
                ", flightDistrictToGoFrom=" + flightDistrictToGoFrom +
                ", flightHelipadNameToGoFrom=" + flightHelipadNameToGoFrom +
                ", district_id_dest=" + district_id_dest +
                ", helipad_id_dest=" + helipad_id_dest +
                ", earlierService='" + earlierService + '\'' +
                ", aadhaar_doc='" + aadhaar_doc + '\'' +
                ", officeCardDoc='" + officeCardDoc + '\'' +
                ", medicalDoc='" + medicalDoc + '\'' +
                ", otherDoc='" + otherDoc + '\'' +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", applicationForwardedToRole=" + applicationForwardedToRole +
                ", applicaionStatus='" + applicaionStatus + '\'' +
                ", concernedAuthorityComments='" + concernedAuthorityComments + '\'' +
                ", userFormDataPreviousServiceEntities=" + userFormDataPreviousServiceEntities +
                '}';
    }
}
