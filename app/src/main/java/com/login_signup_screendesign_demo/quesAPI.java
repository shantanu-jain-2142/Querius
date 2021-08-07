package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 28/9/17.
 */

public interface quesAPI {

    @POST("db_topic_list.php")
    public Call<List<myPojo>> getTopics();
}
