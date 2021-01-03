package com.hp.dit.Flight.Application.Form.projections;

import java.io.Serializable;

public class CheckStatusProjection implements Serializable {

    private String applicationStatus;
    private String comments;
    private Integer userId;

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CheckStatusProjection{" +
                "applicationStatus='" + applicationStatus + '\'' +
                ", comments='" + comments + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
