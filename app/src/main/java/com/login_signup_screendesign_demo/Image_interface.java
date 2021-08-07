package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 27/9/17.
 */

public interface Image_interface {

    @FormUrlEncoded
    @POST("db_image.php")
    public Call<Integer> uploadImage(
            @Field("userid") int user_id,
            @Field("imageurl") String image);

}
