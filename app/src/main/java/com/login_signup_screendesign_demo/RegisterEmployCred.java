package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 8/10/17.
 */

public interface RegisterEmployCred {

    @FormUrlEncoded
    @POST("db_employment_insert.php")
    public Call<Integer> insertEmploy(
            @Field("userid") int id,
            @Field("company") String company,
            @Field("position") String position,
            @Field("syr") int Syr,
            @Field("eyr") int Eyr);
    //           Callback<retrofit2.Response> callback);
}
