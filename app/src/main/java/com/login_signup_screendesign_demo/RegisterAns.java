package com.login_signup_screendesign_demo;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface RegisterAns {

    @FormUrlEncoded
    @POST("db_answer_insert.php")
    public Call<Integer> insertAns(
            @Field("userid") int id,
            @Field("questionid") int qid,
            @Field("atext") String atxt);//           Callback<retrofit2.Response> callback);
}
