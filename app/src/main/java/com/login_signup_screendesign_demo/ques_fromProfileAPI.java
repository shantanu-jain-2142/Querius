package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 4/10/17.
 */

public interface ques_fromProfileAPI {

    @FormUrlEncoded
    @POST("db_ques_fromProfile.php")
    public Call<List<ques_fromProfile_model>> get_ques(@Field("userid") int uid);
}
