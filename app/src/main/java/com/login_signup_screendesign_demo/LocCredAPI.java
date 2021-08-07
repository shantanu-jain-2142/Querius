package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 9/10/17.
 */

public interface LocCredAPI {
    @FormUrlEncoded
    @POST("db_ispresent_loccred.php")
    public Call<Integer> ispresent(@Field("userid") int User_id);
}
