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

public class employment_cred extends AppCompatActivity {

    List<myPojo> list;
    User_Info info;
    final String ROOT_URL = "http://192.168.43.1/sj/";
    final String TAG = "QUES";
    public static Animation shakeAnimation;
    int user_id;
    EditText et1, et2, et3, et4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment_cred);
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
        final EmployCredAPI employAPI = retrofit.create(EmployCredAPI.class);
        final EmployCredAPI2 employAPI2 = retrofit.create(EmployCredAPI2.class);


        //
        Call<Integer> call = employAPI.ispresent(user_id);

        call.enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response)
            {

                if (response.body()==1)
                {
                    int uid=user_id;
                    // Toast.makeText(getApplicationContext(), "already present", Toast.LENGTH_SHORT).show();
                    Call<List<getEmployCred>> call1 = employAPI2.get_employcred(uid);
                    call1.enqueue(new Callback<List<getEmployCred>>()
                    {
                        @Override
                        public void onResponse(Call<List<getEmployCred>> call, Response<List<getEmployCred>> response)
                        {
                            if (response.isSuccessful())
                            {
                                // Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                                List<getEmployCred> list = response.body();
                                getEmployCred em = list.get(0);
                                et1.setText(em.getCompany());
                                et2.setText(em.getPosition());
                                et3.setText(Integer.toString(em.getSyr()));
                                et4.setText(Integer.toString(em.getEyr()));
                                b1.setText("EDIT");

                            } else
                            {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<List<getEmployCred>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"getting id "+t.getMessage());
                            t.printStackTrace();
                        }
                    });

                } else
                {
                    Toast.makeText(getApplicationContext(), "add credentials", Toast.LENGTH_SHORT).show();


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
                get_employ();
            }
        });

    }

    public void get_employ() {

        b1.setText("Done");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RegisterEmployCred employAPI = retrofit.create(RegisterEmployCred.class);
        int syr = Integer.parseInt(et3.getText().toString());
        int eyr = Integer.parseInt(et4.getText().toString());
        String s1 = null, s2 = null;
        s1 = et1.getText().toString();
        s2 = et2.getText().toString();


        Call<Integer> call = employAPI.insertEmploy(user_id,s1,s2,syr,eyr);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value=-1;
                value=response.body();
                Context context = getApplicationContext();
                Toast.makeText(context,Integer.toString(value),Toast.LENGTH_SHORT).show();

                if(value==1){
                   employment_cred.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Employment Credentials Added",Toast.LENGTH_SHORT).show();
                        }
                    });


                    b1.setText("ADDED");

                }
                else{
                    employment_cred.this.runOnUiThread(new Runnable() {
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
