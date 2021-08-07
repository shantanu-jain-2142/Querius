package com.login_signup_screendesign_demo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.graphics.Color.*;
import static android.text.Html.fromHtml;

public class Main3Activity extends AppCompatActivity {

    private String ques,q_user,q_tag,q_top,q_url;
    private String ans_text,ans_user,ans_tag,a_url;
    private int aid,qid,auid,quid;

    User_Info info;
    private TextView tv_ques,tv_q_user,tv_q_tag,tv_ans,tv_ans_tag,tv_ans_user,tv_top;

    ImageView upvote,give_ans,comm,share,ansButton;;
    RoundedImageView ques_profile,ans_profile;

    final private String ROOT_URL = "http://192.168.43.1/sj/";
    final private String TAG = "Answer_Screen";

    LinearLayout linearLayout,linearLayout1;

    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RelativeLayout relativeLayout;

    private List<Spanned> comm_text;
    private List<String> user_name;
    private List<String> user_tag;
    private List<Integer>cid,cuid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(Main3Activity.this);
        recyclerView.setLayoutManager(layoutManager);

        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("ANSWER_INFO");
        ques = bundle.getString("QUESTION");
        q_user = bundle.getString("Q_NAME");
        q_tag = bundle.getString("Q_TAG");
        q_url = bundle.getString("Q_IMAGE");
        a_url = bundle.getString("IMAGE");
        q_top = bundle.getString("Q_Topic");
        ans_text = bundle.getString("ANSWER");
        Log.d("hello","The answer inside main3 is :"+ans_text);
        ans_user = bundle.getString("A_USER");
        ans_tag = bundle.getString("A_TAG");
        info = (User_Info) bundle.getSerializable("Com_object");
//        Log.d("ACHA","Info inside Main3 is :"+info);
        aid=bundle.getInt("ANSWER ID");
        qid=bundle.getInt("QUESTION ID");

        Log.d("hello","inside main3 question id :"+qid);
        quid=bundle.getInt("QUSER ID");

        auid=bundle.getInt("AUSER ID");
            Log.d("hello","inside main3:"+info.getUser_id());


        tv_ques = (TextView) findViewById(R.id.ques);
        tv_q_user = (TextView) findViewById(R.id.tv_name);
        tv_q_tag = (TextView) findViewById(R.id.tv_qual);
        tv_ans = (TextView) findViewById(R.id.answerIshere);
        tv_ans_user = (TextView) findViewById(R.id.tv_name1);
        tv_ans_tag = (TextView) findViewById(R.id.tv_qual1);
        tv_top = (TextView) findViewById(R.id.ques_title);
        upvote = (ImageView) findViewById(R.id.upvote);
        give_ans = (ImageView) findViewById(R.id.ans_btn);
        linearLayout = (LinearLayout) findViewById(R.id.user);
        linearLayout1 = (LinearLayout) findViewById(R.id.user1);
        ques_profile = (RoundedImageView) findViewById(R.id.logo1);
        ans_profile = (RoundedImageView) findViewById(R.id.logo2);
        comm=(ImageView)findViewById(R.id.comment);
        share=(ImageView)findViewById(R.id.share);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
//        ansButton=(ImageView)findViewById(R.id.ans_btn);


        comm_text= new ArrayList<>();
        user_name= new ArrayList<>();
        user_tag= new ArrayList<>();
        cid=new ArrayList<>();
        cuid=new ArrayList<>();

        tv_ques.setText(ques);
        tv_q_user.setText(q_user);
        tv_q_tag.setText(q_tag);
        tv_top.setText(q_top);
        tv_ans.setText(ans_text);
        tv_ans_user.setText(ans_user);
        tv_ans_tag.setText(ans_tag);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
//                Toast.makeText(home_screen_fragment.this.getActivity(),"Tejas",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(Main3Activity.this,"Refreshed!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Main3Activity.this,Main3Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("QUESTION",ques);
                        bundle.putString("Q_NAME",q_user);
                        bundle.putString("Q_TAG",q_tag);
                        bundle.putString("Q_IMAGE",q_url);
                        bundle.putString("Q_Topic",q_top);
                        bundle.putString("ANSWER",ans_text);
                        bundle.putString("A_USER",ans_user);
                        bundle.putString("A_TAG",ans_tag);
                        bundle.putString("IMAGE",a_url);
                        bundle.putSerializable("Com_object",info);
                        bundle.putInt("ANSWER ID",aid);
                        bundle.putInt("AUSER ID",auid);
                        bundle.putInt("QUESTION ID",qid);
                        bundle.putInt("QUSER ID",quid);
                        intent.putExtra("ANSWER_INFO",bundle);
                       startActivity(intent);

//                    getIntent().putExtra("Com_object",userInfo);


                    }
                },1000);
            }
        });


        Picasso.with(getApplicationContext()).load(ROOT_URL+q_url).into(ques_profile);
        Picasso.with(getApplicationContext()).load(ROOT_URL+a_url).into(ans_profile);

        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    DrawableCompat.setTint(upvote.getDrawable(), ContextCompat.getColor(getApplicationContext(),R.color.black));

//                upvote.setColorFilter(getResources().getColor(R.color.dark_greyish));


                DrawableCompat.setTint(upvote.getDrawable(), ContextCompat.getColor(getApplicationContext(),R.color.Red));


                final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
                answer_upvoteAPI upvoteAPI = retrofit.create(answer_upvoteAPI.class);

                Call<Integer> call = upvoteAPI.do_upvote(info.getUser_id(),aid);

                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Log.d("hello","Inside on upvote response");
                        int value=-1;
                        value=response.body();
                        if(value==1){

                            Toast.makeText(getApplicationContext(),"Upvoted",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Not Upvoted",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),"Check Your Network",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        give_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),Answer.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("QUESTION TEXT",ques);
                bundle1.putString("TOPIC NAME",q_top);
                bundle1.putString("IMAGE",q_url);
                bundle1.putString("Q_NAME",q_user);
                bundle1.putString("Q_TAGLINE",q_tag);
                bundle1.putInt("USER ID",info.getUser_id());
                bundle1.putInt("QUESTION ID",qid);
//                bundle1.putSerializable("Com_object",info);
                intent1.putExtras(bundle1);
               startActivity(intent1);


            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Other User","Inside Main3");
                Intent intent = new Intent(Main3Activity.this,Other_user.class);
                intent.putExtra("QUSER ID",quid);
                intent.putExtra("CURR_UID",info.getUser_id());
                startActivity(intent);
            }
        });


        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Other User","Inside Main3");
                Intent intent = new Intent(Main3Activity.this,Other_user.class);
                intent.putExtra("QUSER ID",auid);
                intent.putExtra("CURR_UID",info.getUser_id());
                startActivity(intent);
            }
        });


        comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Main3Activity.this,comment.class);
//                intent.putExtra("key",ans_text);
//                intent.putExtra("key2",info.getUser_id());
//                intent.putExtra("key3",aid);
                Bundle b = new Bundle();
                b.putString("key", ans_text);
                b.putInt("key2",info.getUser_id());
                b.putInt("key3",aid);
                intent1.putExtras(b); //Put your id to your next Intent
                startActivity(intent1);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = ans_text;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });


        get_comments();




    }


    public void get_comments()
    {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        CommentAPI api = retrofit.create(CommentAPI.class);

        Call<List<Comm_model>> model = api.get_all_comm(aid);

        model.enqueue(new Callback<List<Comm_model>>() {
            @Override
            public void onResponse(Call<List<Comm_model>> call, Response<List<Comm_model>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"Inside On Response");
                    List<Comm_model> list = response.body();
                    int i = list.size()-1;
                    while(i>=0){
                        // ans_text.add(list.get(i).getAns_text());
                        Spanned s = null;
                        if (Build.VERSION.SDK_INT >= 24) {
                            s = fromHtml(list.get(i).getComm_text(), Html.FROM_HTML_MODE_LEGACY);
                        }
                        else
                        {
                            s = fromHtml(list.get(i).getComm_text().toString());
                            Toast.makeText(getApplicationContext(),list.get(i).getComm_text().toString(), Toast.LENGTH_SHORT).show();

                        }



                        comm_text.add(s);
                        user_name.add(list.get(i).getU_name());
                        user_tag.add(list.get(i).getU_tag());
                        cid.add(list.get(i).getComm_id());
                        cuid.add(list.get(i).getU_id());
                        i--;
                    }

                    adapter=new  RecyclerViewAdapterComment(auid,aid,cid,cuid,ans_text,ans_user,q_tag,q_top,comm_text,user_name,user_tag, Main3Activity.this,info);
                    recyclerView.setAdapter(adapter);


                }
                else {
                    Log.d(TAG,"Inside onResponse else");
                }
            }

            @Override
            public void onFailure(Call<List<Comm_model>> call, Throwable t) {
                Log.d(TAG,"Inside onFailure");
                t.printStackTrace();
            }
        });
    }

}
