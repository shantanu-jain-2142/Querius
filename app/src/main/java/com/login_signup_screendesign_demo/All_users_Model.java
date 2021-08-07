package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 10/10/17.
 */

public class All_users_Model {

    @SerializedName("User_name")
    private String u_name;

    @SerializedName("userid")
    private int u_id;


    @SerializedName("Image_path")
    private String url;

    public String getU_name() {
        return u_name;
    }

    public int getU_id() {
        return u_id;
    }

    public String getUrl() {
        return url;
    }
}
