package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 11/10/17.
 */

public interface allcredAPI2 {
    @FormUrlEncoded
    @POST("db_get_allcred.php")
    public Call<List<getAllCred>> get_allcred(@Field("userid") int uid);

}
