package com.login_signup_screendesign_demo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onegravity.rteditor.RTEditText;
import com.onegravity.rteditor.RTManager;
import com.onegravity.rteditor.RTToolbar;
import com.onegravity.rteditor.api.RTApi;
import com.onegravity.rteditor.api.RTMediaFactoryImpl;
import com.onegravity.rteditor.api.RTProxyImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class comment extends AppCompatActivity {


    private RTManager rtManager;
    private RTEditText rtEditText;

    List<myPojo> list;
    final String ROOT_URL = "http://192.168.43.1/sj/";
    final String TAG = "QUES";
    public static Animation shakeAnimation;
    int user_id;

    Button addcom;
    List<answerid> id_list;
    int ansid;
    String comm_text=" ";
    String ans_text=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setTheme(R.style.RTE_ThemeLight);
        setContentView(R.layout.activity_comment);

//        Intent intent = new Intent();
//        ans_text = intent.getExtras().getString("key");
//        user_id = intent.getExtras().getInt("key2");
//        ansid = intent.getExtras().getInt("key3");
//        Log.d("hello","inside comment :"+user_id);
        // or other values
        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            ans_text = b.getString("key");
            user_id=b.getInt("key2");
            Log.d("hello","inside comment :"+user_id);
            ansid=b.getInt("key3");
        }

        Toast.makeText(getApplicationContext(), "Comment ", Toast.LENGTH_SHORT).show();
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);


        rtEditText = (RTEditText) findViewById(R.id.rtext);
// create RTManager
       RTApi rtApi = new RTApi(this, new RTProxyImpl(this), new RTMediaFactoryImpl(this, true));
        rtManager = new RTManager(rtApi, savedInstanceState);
        rtManager.registerEditor(rtEditText, true);



        addcom=(Button)findViewById(R.id.addcomm);
        addcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcom.setText("Asking...");



                Editable e=rtEditText.getText();
                comm_text = Html.toHtml(e);

              /*  //--restore from string--
                Spanned s3 = Html.fromHtml(s2);
                et.setText(s3);*/


                if(comm_text.equals("")|| comm_text.length()==0){

                    // new CustomToast().Show_Toast(this, view,"Enter both credentials.");
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Cannot add blank text",Toast.LENGTH_SHORT).show();
                    addcom.setText("Ask");
                }

                else{
                    insert_comm();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onStart() {
        super.onStart();

        // register toolbar
        ViewGroup toolbarContainer = (ViewGroup) findViewById(R.id.rte_toolbar_container);
        RTToolbar rtToolbar = (RTToolbar) findViewById(R.id.rte_toolbar);
        if (rtToolbar != null) {
            rtManager.registerToolbar(toolbarContainer, rtToolbar);
        }

        // register editor & set text
        rtEditText = (RTEditText) findViewById(R.id.rtext);
        // rtManager.registerEditor(rtEditText, true);
    }
//    public void get_comment()
//    {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

//        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//        get_answerid id = retrofit.create(get_answerid.class);


//        Call<List<answerid>> call = id.get_answerid(ans_text);
//
//
//        call.enqueue(new Callback<List<answerid>>()
//        {
//            @Override
//            public void onResponse(Call<List<answerid>> call, Response<List<answerid>> response)
//            {
//                if (response.isSuccessful())
//                {
//                    id_list = response.body();
//                    ansid = id_list.get(0).getAid();
//
//                 //   Log.d(TAG, "Answer ID is :" + ansid);
//                    insert_comm();
//                } else
//                    {
//                    Toast.makeText(getApplicationContext(), "Please Select A valid topic", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<answerid>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Check Your network",Toast.LENGTH_SHORT).show();
//                Log.d(TAG,"getting id "+t.getMessage());
//                t.printStackTrace();
//            }
//        });
//
//
//
//    }

    public void  insert_comm()
    {
       // Log.d(TAG,"Inside insert_comm:"+ansid);
//        Log.d(TAG,"Inside insert_comm:"+info.getUser_id());
   //     Log.d(TAG,"Inside insert_comm:"+comm_text);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RegisterComm commAPI = retrofit.create(RegisterComm.class);

        Call<Integer> call = commAPI
                .insertComm(user_id,ansid,comm_text);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value=-1;
                value=response.body();
                Context context = getApplicationContext();
                Toast.makeText(context,Integer.toString(value),Toast.LENGTH_SHORT).show();

                if(value==1){
                    comment.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Comment Added",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Button addcomm=(Button)findViewById(R.id.addcomm);
                   addcomm.setText("ADDED");

                }
                else{
                    comment.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Comment cannot be added",Toast.LENGTH_SHORT).show();
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