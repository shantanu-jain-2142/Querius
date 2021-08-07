package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 4/10/17.
 */

public interface AnswerAPI {

    @FormUrlEncoded
    @POST("db_get_answer.php")
    public Call<List<Answer_model>> get_all_ans(@Field("questionid") int qid);

}
