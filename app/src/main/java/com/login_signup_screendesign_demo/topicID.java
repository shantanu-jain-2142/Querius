package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 30/9/17.
 */

public class topicID {

    @SerializedName("topicid")
    private int tid;


    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
