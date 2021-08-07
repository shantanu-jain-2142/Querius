package com.login_signup_screendesign_demo;


import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
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



//Image setting remaining
public class Answer extends AppCompatActivity {


    private RTManager rtManager;
    private RTEditText rtEditText;

    List<myPojo> list;
    final String ROOT_URL = "http://192.168.43.1/sj/";
    final String TAG = "QUES";
    public static Animation shakeAnimation;
    int user_id;

    String iurl;
    Button addcom;
    int ques_id;

    List<questionid> id_list;

    private String ques,q_user,q_tag,q_top;
    private String ans_text=" ",ans_user,ans_tag;
    private int aid,qid;
    User_Info info;

    private TextView tv_ques,tv_q_user,tv_q_tag,tv_ans,tv_ans_tag,tv_ans_user,tv_top;

    ImageView upvote,comm,share,ansButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setTheme(R.style.RTE_ThemeLight);
        setContentView(R.layout.activity_answer_);

        Toast.makeText(getApplicationContext(), "Answer ", Toast.LENGTH_SHORT).show();
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);


        Bundle b = getIntent().getExtras();


        if(b!=null) {

//            info = (User_Info) b.getSerializable("Com_object");
            user_id = b.getInt("USER ID");
            Log.d("hello","inside answer :"+user_id);
            q_user = b.getString("Q_NAME");
            ques = b.getString("QUESTION TEXT");
            q_tag = b.getString("Q_TAGLINE");
            q_top = b.getString("TOPIC NAME");
            qid = b.getInt("QUESTION ID");
            iurl = b.getString("IMAGE");
        }

        tv_ques = (TextView) findViewById(R.id.ques);
        tv_q_user = (TextView) findViewById(R.id.tv_name);
        tv_q_tag = (TextView) findViewById(R.id.tv_qual);
        tv_top = (TextView) findViewById(R.id.ques_title);

        upvote = (ImageView) findViewById(R.id.upvote);
        comm=(ImageView)findViewById(R.id.comment);
        share=(ImageView)findViewById(R.id.share);
        ansButton=(ImageView)findViewById(R.id.ans_btn);



       // Toast.makeText(getApplicationContext(), info.getName(), Toast.LENGTH_SHORT).show();

        tv_ques.setText(ques);
        tv_q_user.setText(q_user);
        tv_q_tag.setText(q_tag);
        tv_top.setText(q_top);




        rtEditText = (RTEditText) findViewById(R.id.rtext);
// create RTManager
        RTApi rtApi = new RTApi(this, new RTProxyImpl(this), new RTMediaFactoryImpl(this, true));
        rtManager = new RTManager(rtApi, savedInstanceState);
        rtManager.registerEditor(rtEditText, true);



        addcom=(Button)findViewById(R.id.addans);
        addcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcom.setText("Answering...");
                Editable e=rtEditText.getText();
                ans_text = Html.toHtml(e);

         /*       //Editable e = rtEditText;
                String s2 = Html.toHtml((Spanned) rtEditText);
                Spanned s = null;
               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    ans_text = Html.toHtml(s,Html.FROM_HTML_MODE_LEGACY);
                }*/

              /*  //--restore from string--
                Spanned s3 = Html.fromHtml(s2);
                et.setText(s3);
         */


                if(ans_text.equals("")|| ans_text.length()==0){

                    // new CustomToast().Show_Toast(this, view,"Enter both credentials.");
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Cannot add blank text",Toast.LENGTH_SHORT).show();
                    addcom.setText("Answer");
                }

                else{
                    Context context = getApplicationContext();
//                    Toast.makeText(context,"ANSWER added",Toast.LENGTH_SHORT).show();
                    addcom.setText("Answer");
                   insert_ans();
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

//    public void get_ans()
//    {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//        get_questionid id = retrofit.create(get_questionid.class);
//
//        Call<List<questionid>> call = id.get_questionid(ques);
//
//
//        call.enqueue(new Callback<List<questionid>>()
//        {
//            @Override
//            public void onResponse(Call<List<questionid>> call, Response<List<questionid>> response)
//            {
//                if (response.isSuccessful())
//                {
//                    id_list = response.body();
//                   ques_id = id_list.get(0).getQid();
//
//                    //   Log.d(TAG, "Answer ID is :" + ansid);
//                    insert_ans();
//                } else
//                {
//                    Toast.makeText(getApplicationContext(), "Please Select A valid topic", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<questionid>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Check Your network",Toast.LENGTH_SHORT).show();
//                Log.d(TAG,"getting id "+t.getMessage());
//                t.printStackTrace();
//            }
//        });
//
//
//
//    }
    public void  insert_ans()
    {
        // Log.d(TAG,"Inside insert_comm:"+ansid);
//        Log.d(TAG,"Inside insert_comm:"+info.getUser_id());
        //     Log.d(TAG,"Inside insert_comm:"+comm_text);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RegisterAns ansAPI = retrofit.create(RegisterAns.class);

        Call<Integer> call = ansAPI.insertAns(user_id,qid,ans_text);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value=-1;
                value=response.body();
                Context context = getApplicationContext();
                Toast.makeText(context,Integer.toString(value),Toast.LENGTH_SHORT).show();

                if(value==1){
                    Answer.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Answer Added",Toast.LENGTH_SHORT).show();
                            addcom.setText("Answer");
                        }
                    });

//                    Button addcomm=(Button)findViewById(R.id.addans);
//                    addcomm.setText("ADDED");

                }
                else{
                    Answer.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Answer cannot be added",Toast.LENGTH_SHORT).show();
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