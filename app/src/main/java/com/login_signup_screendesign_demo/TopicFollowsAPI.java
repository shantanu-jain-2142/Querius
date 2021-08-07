package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 28/10/17.
 */

public interface TopicFollowsAPI {

    @FormUrlEncoded
    @POST("topic_follows.php")
    Call<Integer> topicFollows(@Field("userid") int uid,@Field("topicid")int tid);
}
