package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 8/10/17.
 */

public interface FetchImageAPI {

    @FormUrlEncoded
    @POST("db_getImage.php")
    public Call<List<ImageModel>> fetchImage(@Field("User_id") int uid);
}
