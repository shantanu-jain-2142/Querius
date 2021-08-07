package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anuja on 9/10/17.
 */

public class getEmployCred {
    @SerializedName("company")
    private String company;

    @SerializedName("position")
    private String position;

    @SerializedName("startYear")
    private int syr;

    @SerializedName("endYear")
    private int eyr;
    public String getCompany() {
        return company;
    }
    public String getPosition() {
        return position;
    }
    public int getSyr() {
        return syr;
    }
    public int getEyr() {
        return eyr;
    }

}
