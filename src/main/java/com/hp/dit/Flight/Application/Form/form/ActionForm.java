package com.hp.dit.Flight.Application.Form.form;


import java.io.Serializable;

public class ActionForm implements Serializable {

    private String action;
    private String comments;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ActionForm{" +
                "action='" + action + '\'' +
                ", comments='" + comments + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
