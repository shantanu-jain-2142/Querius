package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 4/10/17.
 */

public class Answer_model {

    @SerializedName("answerid")
    private int ans_id;


    @SerializedName("userid")
    private int u_id;


    @SerializedName("atext")
    private String ans_text;

    @SerializedName("User_name")
    private String u_name;

    @SerializedName("tagline")
    private String u_tag;


    @SerializedName("Image_path")
    private String img_path;

    public String getImg_path() {
        return img_path;
    }

    public int getU_id() {
        return u_id;
    }

    public int getAns_id() {
        return ans_id;
    }


    public String getU_tag() {
        return u_tag;
    }

    public String getAns_text() {

        return ans_text;
    }

    public String getU_name() {
        return u_name;
    }
}
