package com.login_signup_screendesign_demo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shantanu on 7/10/17.
 */

public interface FollowAPI {

    @FormUrlEncoded
    @POST("db_follow.php")
    public Call<Integer> to_follow(@Field("userid") int uid, @Field("followsId") int fid);

}
