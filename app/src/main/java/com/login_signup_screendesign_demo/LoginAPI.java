package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 20/9/17.
 */

public interface LoginAPI {

    @FormUrlEncoded
    @POST("db_validate.php")
    public Call<Integer> validateUser(
            @Field("email") String email,
            @Field("password") String passwd);//           Callback<retrofit2.Response> callback);
}
