package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 8/10/17.
 */

public interface ImageInterface {


    @FormUrlEncoded
    @POST("upload_image.php")
    public Call<Integer> uploadImage(@Field("Image_title") String title,@Field("image") String img,@Field("User_id") int uid);
}
