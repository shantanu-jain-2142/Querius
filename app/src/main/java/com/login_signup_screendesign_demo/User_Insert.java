package com.login_signup_screendesign_demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 20/9/17.
 */

public class User_Insert {

    @SerializedName("user_info")
    @Expose
    private User_Info user_info;

    public User_Info getUser_info() {
        return user_info;
    }

    public void setUser_info(User_Info user_info) {
        this.user_info = user_info;
    }
}
