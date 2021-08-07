package com.login_signup_screendesign_demo;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 1/10/17.
 */

public interface get_user_info {

    @FormUrlEncoded
    @POST("db_get_user_info.php")
    public Call<List<User_Info>> u_info(@Field("userid") int User_id);
}
