package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 10/10/17.
 */

public interface EmployCredAPI2 {
    @FormUrlEncoded
    @POST("db_get_employcred.php")
    public Call<List<getEmployCred>> get_employcred(@Field("userid") int uid);
}
