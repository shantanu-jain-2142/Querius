package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 6/10/17.
 */

public interface get_answerid {
    @FormUrlEncoded
    @POST("db_get_answerid.php")
    public Call<List<answerid>> get_answerid(@Field("name") String answer_text);

}
