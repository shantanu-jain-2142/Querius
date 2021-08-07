package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 8/10/17.
 */

public interface RegisterEduCred {
    @FormUrlEncoded
    @POST("db_education_insert.php")
    public Call<Integer> insertEdu(
            @Field("userid") int id,
            @Field("sch") String school,
            @Field("degree") String degree,
            @Field("branch") String branch,
            @Field("Gyear") int yr);
             //           Callback<retrofit2.Response> callback);
}
