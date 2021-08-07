package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 2/10/17.
 */

public class getUser_Info {

    @SerializedName("User_name")
    private String user_name;

//    @SerializedName("imageurl")
//    private String user_image;


    @SerializedName("tagline")
    private String user_tag;


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

//    public String getUser_image() {
//        return user_image;
//    }
//
//    public void setUser_image(String user_image) {
//        this.user_image = user_image;
//    }

    public String getUser_tag() {
        return user_tag;
    }

    public void setUser_tag(String user_tag) {
        this.user_tag = user_tag;
    }
}
