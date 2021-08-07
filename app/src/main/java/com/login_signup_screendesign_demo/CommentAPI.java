package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 9/10/17.
 */

public interface CommentAPI {
    @FormUrlEncoded
    @POST("db_get_comment.php")
    public Call<List<Comm_model>> get_all_comm(@Field("answerid") int aid);

}
