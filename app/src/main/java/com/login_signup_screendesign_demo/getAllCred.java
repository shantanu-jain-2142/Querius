package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 11/10/17.
 */

public class getAllCred {
    @SerializedName("school")
    private String school;

    @SerializedName("degree")
    private String degree;

    @SerializedName("branch")
    private String branch;

    @SerializedName("gradYear")
    private int gyear;

    @SerializedName("company")
    private String company;

    @SerializedName("position")
    private String position;

    @SerializedName("startYear")
    private int syr;

    @SerializedName("endingYear")
    private int eyr;

    @SerializedName("location")
    private String location;

    @SerializedName("startYear2")
    private int syr2;

    @SerializedName("endYear2")
    private int eyr2;

    public String getLocation() {
        return location;
    }
    public int getSyr2() {
        return syr2;
    }
    public int getEyr2() {
        return eyr2;
    }

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
