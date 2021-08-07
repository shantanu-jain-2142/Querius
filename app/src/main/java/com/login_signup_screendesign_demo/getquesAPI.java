package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by shantanu on 30/9/17.
 */

public interface getquesAPI {

    @POST("db_ques_list.php")
    public Call<List<getQuestions>> getQues();
}
