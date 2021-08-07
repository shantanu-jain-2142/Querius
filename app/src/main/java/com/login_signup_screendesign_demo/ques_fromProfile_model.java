package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 4/10/17.
 */

public class ques_fromProfile_model {



    @SerializedName("questionid")
    private int qid;

    @SerializedName("userid")
    private int quid;


    @SerializedName("qtext")
    private String qtxt;

    @SerializedName("name")
    private String t_name;



    public int getQuid() {
        return quid;
    }

    public int getQid() {
        return qid;
    }


    public String getQtxt() {
        return qtxt;
    }

    public String getT_name() {
        return t_name;
    }
}
