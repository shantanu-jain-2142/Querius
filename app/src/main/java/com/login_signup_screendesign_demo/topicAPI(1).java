package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 3/10/17.
 */

public interface topicAPI {

    @FormUrlEncoded
    @POST("db_gettopic_name.php")
    public Call<List<topic_details>> get_topic_details(@Field("topicid") int tid);

}
