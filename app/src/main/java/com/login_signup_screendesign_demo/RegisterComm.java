package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anuja on 6/10/17.
 */

public interface RegisterComm {

    @FormUrlEncoded
    @POST("db_comment_insert.php")
    public Call<Integer> insertComm(
            @Field("userid") int id,
            @Field("answerid") int aid,
            @Field("ctext") String ctxt);//           Callback<retrofit2.Response> callback);
}
