package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 11/10/17.
 */

public interface allcred {
    @FormUrlEncoded
    @POST("db_credentials.php")
    public Call<Integer> ispresent(@Field("userid") int uid);

}
