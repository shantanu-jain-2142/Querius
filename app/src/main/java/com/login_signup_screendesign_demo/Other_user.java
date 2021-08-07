package com.login_signup_screendesign_demo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Other_user extends AppCompatActivity {

    private int uid,curr_uid;
    private String img_url;
    final private String ROOT_URL = "http://192.168.43.1/sj/";
    private TextView tv_name,tv_tag,tv_ques_asked,tv_ans_given,tv_foll;
    RoundedImageView other_dp;
    private FloatingActionButton to_f,no_f;
    static int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_user_profile);

        Intent intent = getIntent();
        uid = intent.getIntExtra("QUSER ID",-1);
        curr_uid = intent.getIntExtra("CURR_UID",-1);
        img_url = intent.getStringExtra("IMAGE");

        Log.d("Other","uid = "+uid);
        Log.d("Other","curr_uid = "+curr_uid);

        tv_name = (TextView) findViewById(R.id.name);
        tv_tag = (TextView) findViewById(R.id.email_id);
        tv_ques_asked = (TextView) findViewById(R.id.no1);
        tv_ans_given = (TextView) findViewById(R.id.no2);
        tv_foll = (TextView) findViewById(R.id.no3);
        to_f = (FloatingActionButton) findViewById(R.id.to_foll);
        other_dp = (RoundedImageView) findViewById(R.id.logo_other);

        Picasso.with(getApplicationContext()).load(ROOT_URL+img_url).into(other_dp);
//        no_f = (FloatingActionButton) findViewById(R.id.no_foll);


        if(flag==1){
            to_f.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp);
        }
        else {
            to_f.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp);
        }
        to_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(flag==1){
//                    to_f.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp);
//                }
//                else {
//                    to_f.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp);
//                }
                to_f.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp);
                to_follow();
            }
        });


        get_user_information();
    }


    private void get_user_information(){

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        get_user_info info = retrofit.create(get_user_info.class);

        Call<List<User_Info>> call = info.u_info(uid);

        call.enqueue(new Callback<List<User_Info>>() {
            @Override
            public void onResponse(Call<List<User_Info>> call, Response<List<User_Info>> response) {
                if(response.isSuccessful()){
                    Log.d("Other","Inside onResponse");
                    List<User_Info> list = response.body();
                    User_Info user_info = list.get(0);
                    Log.d("Other","Followers are "+user_info.getFollower());
                    tv_name.setText(user_info.getName());
                    tv_tag.setText(user_info.getTagLine());
                    tv_foll.setText(""+user_info.getFollower());
                    tv_ques_asked.setText(""+user_info.getQues_asked());
                    tv_ans_given.setText(""+user_info.getAns_given());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Some Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User_Info>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Check Your Network Connection",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void to_follow(){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        FollowAPI api = retrofit.create(FollowAPI.class);
        Call<Integer> call = api.to_follow(curr_uid,uid);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value = response.body();
                flag = value;

                if(value==1){

                    Toast.makeText(getApplicationContext(),"Successfully Followed",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Already Following",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Check Your Network",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
