package com.login_signup_screendesign_demo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 30/9/17.
 */

public interface getTopicID {

    @FormUrlEncoded
    @POST("db_getTopic_id.php")
    public Call<List<topicID>> getTopic_id(@Field("name") String topic_name);

}
