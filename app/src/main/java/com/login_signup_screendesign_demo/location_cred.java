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

public class location_cred extends AppCompatActivity {

    List<myPojo> list;
    User_Info info;
    final String ROOT_URL = "http://192.168.43.1/sj/";
    final String TAG = "QUES";
    public static Animation shakeAnimation;
    int user_id;
    EditText et1, et2, et3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_cred);
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
             Toast.makeText(getApplicationContext(),"elsee",Toast.LENGTH_SHORT).show();
            user_id=0;
        }
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        et1 = (EditText) findViewById(R.id.editText2);
        et2 = (EditText) findViewById(R.id.editText3);
        et3 = (EditText) findViewById(R.id.editText4);
        b1 = (Button) findViewById(R.id.button3);
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final LocCredAPI locAPI = retrofit.create(LocCredAPI.class);
        final LocCredAPI2 locAPI2 = retrofit.create(LocCredAPI2.class);


        //
        Call<Integer> call = locAPI.ispresent(user_id);

        call.enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response)
            {

                if (response.body()==1)
                {
                    int uid=user_id;
                    // Toast.makeText(getApplicationContext(), "already present", Toast.LENGTH_SHORT).show();
                    Call<List<getLocCred>> call1 = locAPI2.get_loccred(uid);
                    call1.enqueue(new Callback<List<getLocCred>>()
                    {
                        @Override
                        public void onResponse(Call<List<getLocCred>> call, Response<List<getLocCred>> response)
                        {
                            if (response.isSuccessful())
                            {
                                // Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                                List<getLocCred> list = response.body();
                                getLocCred lo = list.get(0);
                                et1.setText(lo.getLocation());
                                et2.setText(Integer.toString(lo.getSyr()));
                                et3.setText(Integer.toString(lo.getEyr()));

                                b1.setText("EDIT");

                            } else
                            {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<List<getLocCred>> call, Throwable t) {
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
                get_loc();
            }
        });

    }

    public void get_loc() {

        b1.setText("Done");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RegisterLocCred locAPI = retrofit.create(RegisterLocCred.class);
        int syr = Integer.parseInt(et2.getText().toString());
        int eyr = Integer.parseInt(et3.getText().toString());
        String s1 = null;
        s1 = et1.getText().toString();

        Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),et2.getText().toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),et3.getText().toString(),Toast.LENGTH_SHORT).show();

        Call<Integer> call = locAPI.insertLoc(user_id,s1,syr,eyr);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value=-1;
                value=response.body();
                Context context = getApplicationContext();
                Toast.makeText(context,Integer.toString(value),Toast.LENGTH_SHORT).show();

                if(value==1){
                   location_cred.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Location Credentials Added",Toast.LENGTH_SHORT).show();
                        }
                    });


                    b1.setText("ADDED");

                }
                else{
                    location_cred.this.runOnUiThread(new Runnable() {
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
