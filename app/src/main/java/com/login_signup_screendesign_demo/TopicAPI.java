package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by shantanu on 24/10/17.
 */

public interface TopicAPI {

    @POST("get_all_topics.php")
    Call<List<Topic_model>> getTopics();
}
