package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 24/10/17.
 */

public class Topic_model {

    @SerializedName("name")
    private String t_name;

    @SerializedName("topicid")
    private int t_id;

    public String getT_name() {
        return t_name;
    }

    public int getT_id() {
        return t_id;
    }
}
