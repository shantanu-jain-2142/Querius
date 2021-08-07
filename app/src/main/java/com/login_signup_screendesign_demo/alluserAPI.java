package com.login_signup_screendesign_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by shantanu on 10/10/17.
 */

public interface alluserAPI {

    @POST("db_get_all_users.php")
    public Call<List<All_users_Model>> getAllusers();

}
