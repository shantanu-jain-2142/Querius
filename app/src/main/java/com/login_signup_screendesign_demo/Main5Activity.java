package com.login_signup_screendesign_demo;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main5Activity extends AppCompatActivity {


    final private String ROOT_URL = "http://192.168.43.1/sj/";
    final private String TAG = "Main5";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RelativeLayout relativeLayout;


    CoordinatorLayout coordinatorLayout;



    private List<String> topic_list;
    private List<Integer> topic_id;

    boolean flag1[],count=false;
    User_Info info;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

//        button = (Button) findViewById(R.id.button);
        Intent intent = getIntent();
        info = (User_Info) intent.getExtras().getSerializable("Object");
//        Log.d("sab","inside M2"+userInfo.getName());;
        Log.d(TAG,"info inside MAin5 is :"+info);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(Main5Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        button = (Button) findViewById(R.id.next_button);

        relativeLayout = (RelativeLayout) findViewById(R.id.hs_rel);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.co_layout);

        topic_list = new ArrayList<>();
        topic_id = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=false;
                for(int i=0;i<topic_id.size();i++){
                    if(flag1[i]==true){
                        topic_follows(i);
                        count = true;
                    }
                }
                if(!count){
                    Toast.makeText(Main5Activity.this,"Please Select Atleast one topic",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(Main5Activity.this, MainActivity.class);
                    intent1.putExtra("Object", info);
                    startActivity(intent1);
                }


            }
        });

        get_topics();

//        final String [] listn={"Software Development","Machine Learning","Artificial Learning","Mechanics","Mettalurgy","Soft Skills","Gaming"};
//        flag=new int[listn.length];
//        for(int j=0;j<listn.length;j++)
//        {
//            flag[j]=0;
//        }
//        ListAdapter ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listn);
//        final ListView v1=(ListView)findViewById(R.id.QListView);
//        v1.setAdapter(ad);
//
//        v1.setOnItemClickListener(
//                new AdapterView.OnItemClickListener(){
//
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        String name=String.valueOf(adapterView.getItemAtPosition(i));
//
//                        try{
//                            for (int ctr=0;ctr<=listn.length;ctr++){
//                                if(i==ctr)
//                                {
//                                    if(flag[ctr]==0)
//                                    { v1.getChildAt(ctr).setBackgroundColor(getResources().getColor(R.color.background_color));
//                                        Toast.makeText(Main5Activity.this, name+" selected", Toast.LENGTH_SHORT).show();
//                                        flag[ctr]=1;}
//                                    else{
//                                        v1.getChildAt(ctr).setBackgroundColor(Color.WHITE);
//                                        Toast.makeText(Main5Activity.this, name+" unselected", Toast.LENGTH_SHORT).show();
//                                        flag[ctr]=0;
//                                    }
//                                }
//                            }
//                        }
//                        catch (Exception e){
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//
//        );



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Main5Activity.this,MainActivity.class);
//                intent1.putExtra("Object",info);
//                startActivity(intent1);
//            }
//        });


    }


    DataTransfer dataTransfer = new DataTransfer() {
        @Override
        public void send(boolean[] flag) {
            flag1=flag;
        }
    };



    private void get_topics(){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        TopicAPI api = retrofit.create(TopicAPI.class);
        Call<List<Topic_model>> call = api.getTopics();

        call.enqueue(new Callback<List<Topic_model>>() {
            @Override
            public void onResponse(Call<List<Topic_model>> call, Response<List<Topic_model>> response) {
                List<Topic_model> list = response.body();
                int i=0;
                while (i<list.size()){
                    topic_list.add(list.get(i).getT_name());
                    Log.d(TAG,"topic item is: "+topic_list.get(i));
                    topic_id.add(list.get(i).getT_id());
                    i++;
                }

                adapter = new RecyclerAdapterTopics(topic_list,topic_id,Main5Activity.this,dataTransfer);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Topic_model>> call, Throwable t) {
                Toast.makeText(Main5Activity.this,"Check Your Network",Toast.LENGTH_SHORT);
                t.printStackTrace();
            }
        });


    }

    private void topic_follows(int i){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        TopicFollowsAPI api = retrofit.create(TopicFollowsAPI.class);

        Call<Integer> call = api.topicFollows(info.getUser_id(),topic_id.get(i));

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value = response.body();
                if(value==1){
                    Log.d(TAG,"Insertion Successful");
                }
                else {
                    Log.d(TAG,"Insertion UnSuccessful");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(Main5Activity.this,"Check Your Network",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }



}
