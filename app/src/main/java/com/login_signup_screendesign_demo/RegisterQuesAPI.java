package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 30/9/17.
 */

public interface RegisterQuesAPI {

    @FormUrlEncoded
    @POST("db_ques_insert.php")
    public Call<Integer> insertQues(
            @Field("userid") int id,
            @Field("qtopic_id") int tid,
            @Field("qtext") String qtxt);//           Callback<retrofit2.Response> callback);
}

