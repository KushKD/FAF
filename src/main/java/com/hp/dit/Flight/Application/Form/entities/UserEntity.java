package com.hp.dit.Flight.Application.Form.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "users_user_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "public.users_user_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "active")
    private boolean active;

    @Column(name = "is_deleted")
    private boolean is_deleted;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_mapping", joinColumns = @JoinColumn(name = "rm_user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rm_role_id", referencedColumnName = "role_id"))

    //   @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolesEntity> roles;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", active=" + active +
                ", is_deleted=" + is_deleted +
                ", roles=" + roles +
                '}';
    }
}