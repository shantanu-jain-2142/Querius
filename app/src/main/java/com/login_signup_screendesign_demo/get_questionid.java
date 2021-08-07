package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 7/10/17.
 */
public interface get_questionid {
    @FormUrlEncoded
    @POST("db_get_questionid.php")
    public Call<List<questionid>> get_questionid(@Field("name") String question_text);

}