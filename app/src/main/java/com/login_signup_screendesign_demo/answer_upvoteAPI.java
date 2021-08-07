package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 5/10/17.
 */

public interface answer_upvoteAPI {

    @FormUrlEncoded
    @POST("db_upvote_ans.php")
    public Call<Integer> do_upvote(@Field("userid") int uid,@Field("answerid") int ans_id);

}
