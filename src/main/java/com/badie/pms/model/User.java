package com.badie.pms.model;

import java.util.Date;

public class User {
    public int user_id;
    public String user_email;
    public String user_pass;
    public String user_type;
    public User(int user_id, String user_email, String user_pass, String user_type) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_type = user_type;
    }

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_email='" + user_email + '\'' +
                ", user_pass='" + user_pass + '\'' +
                ", user_type='" + user_type + '\'' +
                '}';
    }
}
