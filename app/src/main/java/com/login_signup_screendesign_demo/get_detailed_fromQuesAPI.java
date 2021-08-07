package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 1/10/17.
 */

public interface get_detailed_fromQuesAPI {

    @FormUrlEncoded
    @POST("db_get_home.php")
    public Call<List<get_detailed_fromQues>> getQues(@Field("userid") int uid);
}
