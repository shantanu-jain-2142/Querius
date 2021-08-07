package com.login_signup_screendesign_demo;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shantanu on 19/9/17.
 */

public class    User_Info implements Serializable {


    @SerializedName("Image_ID")
    private int img_id;

    @SerializedName("Image_title")
    private String img_title;

    @SerializedName("Image_path")
    private Bitmap img_url;

    @SerializedName("userid")
    @Expose
    private int user_id;

    @SerializedName("imageurl")
    @Expose
    private String imageURL;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @SerializedName("email")
    @Expose
    private  String email_id;

    @SerializedName("User_name")
    @Expose
    private  String name;

    @SerializedName("password")
    @Expose
    private  String password;

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public void setImg_title(String img_title) {
        this.img_title = img_title;
    }

    public void setImg_url(Bitmap img_url) {
        this.img_url = img_url;
    }

    public String getImg_title() {
        return img_title;
    }

    public Bitmap getImg_url() {
        return img_url;
    }

    public int getQues_asked() {
        return ques_asked;
    }

    public void setQues_asked(int ques_asked) {
        this.ques_asked = ques_asked;
    }

    public int getAns_given() {
        return ans_given;
    }

    public void setAns_given(int ans_given) {
        this.ans_given = ans_given;
    }

    public int getBook_marks() {
        return book_marks;
    }

    public void setBook_marks(int book_marks) {
        this.book_marks = book_marks;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    @SerializedName("location")
    @Expose
    private  String address;

    @SerializedName("tagline")
    @Expose
    private  String tagLine;

    @SerializedName("Questions_asked")
    @Expose
    private int ques_asked;

    @SerializedName("Answers_given")
    @Expose
    private int ans_given;

    @SerializedName("bookmarks")
    @Expose
    private int book_marks;

    @SerializedName("upvoted")
    @Expose
    private int upvote;

    @SerializedName("followers")
    @Expose
    private int follower;


    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }
}
