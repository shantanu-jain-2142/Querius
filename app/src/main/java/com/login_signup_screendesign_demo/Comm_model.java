package com.login_signup_screendesign_demo;

/**
 * Created by anuja on 9/10/17.
 */

import com.google.gson.annotations.SerializedName;


public class Comm_model {

    @SerializedName("commid")
    private int comm_id;


    @SerializedName("userid")
    private int u_id;


    @SerializedName("ctext")
    private String comm_text;

    @SerializedName("User_name")
    private String u_name;

    @SerializedName("tagline")
    private String u_tag;


    public int getU_id() {
        return u_id;
    }

    public int getComm_id() {
        return comm_id;
    }


    public String getU_tag() {
        return u_tag;
    }

    public String getComm_text() {

        return comm_text;
    }

    public String getU_name() {
        return u_name;
    }
}
