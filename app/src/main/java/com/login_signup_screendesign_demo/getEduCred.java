package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anuja on 9/10/17.
 */

public class getEduCred {
    @SerializedName("school")
    private String school;

    @SerializedName("degree")
    private String degree;

    @SerializedName("branch")
    private String branch;

    @SerializedName("gradYear")
    private int gyear;
    public String getSchool() {
        return school;
    }
    public String getDegree() {
        return degree;
    }

    public String getBranch() {
        return branch;
    }
    public int getGYear() {
        return gyear;
    }



}
