package com.login_signup_screendesign_demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class education_cred extends AppCompatActivity {

    List<myPojo> list;
    User_Info info;
    final String ROOT_URL = "http://192.168.43.1/sj/";
    final String TAG = "QUES";
    public static Animation shakeAnimation;
    int user_id;
    EditText et1, et2, et3, et4;
    Button b1;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_cred);
     /*   Intent i = this.getIntent();

        info = (User_Info) i.getSerializableExtra("info");
        if (info != null) {
            Log.d("sab", "inside profile" + info.getQues_asked());
            user_id = info.getUser_id();
        } else {
            user_id = 0;
        }*/

        Bundle b = getIntent().getExtras();
        // or other values
        if(b != null)
        {
            user_id=b.getInt("key");
           // Toast.makeText(getApplicationContext(),Integer.toString(user_id),Toast.LENGTH_SHORT).show();
        }
        else
        {
       // Toast.makeText(getApplicationContext(),"elsee",Toast.LENGTH_SHORT).show();
            user_id=0;
        }
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        et1 = (EditText) findViewById(R.id.editText2);
        et2 = (EditText) findViewById(R.id.editText3);
        et3 = (EditText) findViewById(R.id.editText4);
        et4 = (EditText) findViewById(R.id.editText5);
        b1 = (Button) findViewById(R.id.button3);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final EduCredAPI eduAPI = retrofit.create(EduCredAPI.class);
        final EduCredAPI2 eduAPI2 = retrofit.create(EduCredAPI2.class);


        //
        Call<Integer> call = eduAPI.ispresent(user_id);

        call.enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response)
            {

                if (response.body()==1)
                {
                    int uid=user_id;
                   // Toast.makeText(getApplicationContext(), "already present", Toast.LENGTH_SHORT).show();
                    Call<List<getEduCred>> call1 = eduAPI2.get_educred(uid);
                    call1.enqueue(new Callback<List<getEduCred>>()
                    {
                        @Override
                        public void onResponse(Call<List<getEduCred>> call, Response<List<getEduCred>> response)
                        {
                            if (response.isSuccessful())
                            {
                               // Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                                List<getEduCred> list = response.body();
                                getEduCred edu = list.get(0);
                                et1.setText(edu.getSchool());
                                et2.setText(edu.getDegree());
                                et3.setText(edu.getBranch());
                                et4.setText(Integer.toString(edu.getGYear()));
                                b1.setText("EDIT");
                                flag=1;
                            } else
                            {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<List<getEduCred>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"getting id "+t.getMessage());
                            t.printStackTrace();
                        }
                    });

                } else
                {
                    Toast.makeText(getApplicationContext(), "add cred", Toast.LENGTH_SHORT).show();


                }


            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"getting id "+t.getMessage());
                t.printStackTrace();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_edu();
            }
        });

    }


    public void get_edu()
     {

        b1.setText("Done");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RegisterEduCred eduAPI = retrofit.create(RegisterEduCred.class);
        int yr = Integer.parseInt(et4.getText().toString());
        String s1 = null, s2 = null, s3 = null;
        s1 = et1.getText().toString();
        s2 = et2.getText().toString();
        s3 = et3.getText().toString();


           Call<Integer> call = eduAPI.insertEdu(user_id,s1,s2,s3,yr);

            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    int value=-1;
                    value=response.body();
                    Context context = getApplicationContext();
                    Toast.makeText(context,Integer.toString(value),Toast.LENGTH_SHORT).show();

                    if(value==1){
                        education_cred.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Educational Credentials Added",Toast.LENGTH_SHORT).show();
                            }
                        });


                        b1.setText("ADDED");

                    }
                    else{
                        education_cred.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Credentials cannot be added",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }


                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"inserting"+t.getMessage());
                }
            });
        }

    }
