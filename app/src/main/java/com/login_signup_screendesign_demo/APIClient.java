package com.login_signup_screendesign_demo;

import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shantanu on 20/9/17.
 */

public interface APIClient {

    @FormUrlEncoded
    @POST("db_forgot.php")
    Call<List<User_Info>> getUser_Info(@Field("email") String email);
}
