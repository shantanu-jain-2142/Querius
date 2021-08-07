package com.login_signup_screendesign_demo;


import android.database.Observable;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.DataBufferObserver;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class home_screen_fragment extends Fragment {



    private List<String> ques_list,user_name,user_tagline,topic_name,img_url;

    private List<get_detailed_fromQues> list;

    private List<String> user_image;

    private List<Integer>qid,uid;



    private getUser_Info uinfo;

    final private String ROOT_URL = "http://192.168.43.1/sj/";
    final private String TAG = "AdapterinHome";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RelativeLayout relativeLayout;


    CoordinatorLayout coordinatorLayout;

    SwipeRefreshLayout swipeRefreshLayout;
    User_Info info;

    public home_screen_fragment() {
        // Required empty public constructor

    }



    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void respond(int uid,int qid,String name, String tag, String qtxt,String t_name,String i_url) {
            home2_screen fragmentB=new home2_screen();
            Bundle bundle=new Bundle();
            bundle.putString("NAME",name);
            bundle.putString("TAGLINE",tag);
            bundle.putString("QUESTION",qtxt);
            bundle.putString("TOPIC",t_name);
            bundle.putString("IMAGE",i_url);
            bundle.putInt("QUESTION ID",qid);
            bundle.putInt("USER ID",uid);
            bundle.putSerializable("Com_object",info);
            fragmentB.setArguments(bundle);
            FragmentManager manager=getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.hs_rel,fragmentB).addToBackStack(null).commit();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_screen_rel_layout, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(home_screen_fragment.this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        relativeLayout = (RelativeLayout) v.findViewById(R.id.hs_rel);
        coordinatorLayout = (CoordinatorLayout) v.findViewById(R.id.co_layout);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe);

        info = (User_Info) getArguments().getSerializable("Com_object");
        Log.d("hello","Info is :"+info);
        if(info != null){
            Log.d(TAG,"Inside info:"+info.getEmail_id());
        }


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
//                Toast.makeText(home_screen_fragment.this.getActivity(),"Tejas",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(home_screen_fragment.this.getActivity(),"REFRESHED!",Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager1 = getFragmentManager();
//                    getIntent().putExtra("Com_object",userInfo);
                        home_screen_fragment fragment123 = new home_screen_fragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Com_object",info);
                        fragment123.setArguments(bundle);
                        fragmentManager1.beginTransaction().replace(R.id.content,fragment123).addToBackStack(null).commit();

                    }
                },1000);
            }
        });




        ques_list= new ArrayList<>();
        user_name= new ArrayList<>();
        user_image= new ArrayList<>();
        user_tagline= new ArrayList<>();
        topic_name = new ArrayList<>();
        img_url = new ArrayList<>();
        qid=new ArrayList<>();
        uid=new ArrayList<>();




        getQues();




        return v;


    }


    private void getQues(){

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        get_detailed_fromQuesAPI fromQues = retrofit.create(get_detailed_fromQuesAPI.class);

        Call<List<get_detailed_fromQues>> call = fromQues.getQues(info.getUser_id());

        call.enqueue(new Callback<List<get_detailed_fromQues>>() {
            @Override
            public void onResponse(Call<List<get_detailed_fromQues>> call, Response<List<get_detailed_fromQues>> response) {
                if(response.isSuccessful()) {

                    Log.d(TAG, "Inside onResponse");
                    list = response.body();
                    int i = list.size()-1;
//                    int uid = -1;
                    while (i >= 0) {
                        ques_list.add(list.get(i).getQues_text());
                        Log.d(TAG, "Ques is :" + list.get(i).getQues_text());
                        user_name.add(list.get(i).getUser_name());
                        Log.d(TAG,"The user name is:"+list.get(i).getUser_name());
                        user_tagline.add(list.get(i).getUser_tag());
                        topic_name.add(list.get(i).getTopic_name());
                        qid.add(list.get(i).getQues_id());
                        uid.add(list.get(i).getUser_id());
                        img_url.add(list.get(i).getImg_path());
                        i--;
                    }
                    adapter = new RecyclerAdapter(uid,qid,ques_list, user_name, user_tagline,topic_name,img_url,getChildFragmentManager(),recyclerView,communication,getContext(),info);
                    recyclerView.setAdapter(adapter);

                }
                else{
                    Log.d(TAG,"Inside onResponse else");
                }
            }

            @Override
            public void onFailure(Call<List<get_detailed_fromQues>> call, Throwable t) {

                Log.d(TAG,"Inside onFailure");
                t.printStackTrace();
            }
        });




    }


//
//    private void get_user_information(final int i){
//
//        int uid=-1;
//
//
//            Log.d(TAG,"The value of i is:"+i);
//
//
//            if(i==user_id.size()){
//                Log.d(TAG,"Data Finished");
//                return;
//            }
//
////
////            while (i<user_id.size()) {
//                Log.d(TAG, "Inside getuserinfo");
//
//                uid = user_id.get(i);
//
////            Log.d(TAG,"The user id is :"+uid);
//
//
////            function1();
//
//                final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
//                get_user_info info = retrofit.create(get_user_info.class);
//
//                Call<List<getUser_Info>> call = info.u_info(uid);
//
//
//                Log.d(TAG, "The user id is : " + uid);
//                final int finalI = i;
//
//
//        final int finalI1 = i;
//        call.enqueue(new Callback<List<getUser_Info>>() {
//                    @Override
//                    public void onResponse(Call<List<getUser_Info>> call, Response<List<getUser_Info>> response) {
//                        if (response.isSuccessful()) {
//                            Log.d(TAG, "Inside getting user info");
//                            List<getUser_Info> uinfo = response.body();
//                            getUser_Info userInfo = uinfo.get(0);
//                            user_name.add(userInfo.getUser_name());
//                            Log.d(TAG, "User Name" + user_name.get(finalI));
//                            user_tagline.add(userInfo.getUser_tag());
//
//                            if(finalI==user_id.size()-1) {
//                                get_topic_name(0);
//                            }
////                        user_image.add(userInfo.getUser_image());
//                                 get_user_information(i+1);
//
//
//                        } else {
//                            Log.d(TAG, "The User does not exists");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<getUser_Info>> call, Throwable t) {
//
//                        Log.d(TAG, "Inside user onFailure");
//                    }
//                });
//
////                i++;
////            }
//
//
//
//
//    }
//
//
//
//    private void get_topic_name(final int i){
//
//            int tid=-1;
//
//
//        if(i==topic_id.size()){
//            Log.d(TAG,"Data Finished inside topic");
//            return;
//        }
//
//        tid = topic_id.get(i);
//
//        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        topicAPI tapi = retrofit.create(topicAPI.class);
//
//        Call<List<topic_details>> call = tapi.get_topic_details(tid);
//
//        call.enqueue(new Callback<List<topic_details>>() {
//            @Override
//            public void onResponse(Call<List<topic_details>> call, Response<List<topic_details>> response) {
//                if(response.isSuccessful()){
//                    List<topic_details> list = response.body();
//                    topic_name.add(list.get(0).getTopic_name());
//                    Log.d(TAG,"The topic name is :"+topic_name.get(i));
//                    if(i==topic_id.size()-1) {
//                        adapter = new RecyclerAdapter(ques_list, user_name, user_tagline, user_image,topic_name);
//                        recyclerView.setAdapter(adapter);
//                    }
//                    get_topic_name(i+1);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<topic_details>> call, Throwable t) {
//                Log.d(TAG, "Inside topic onFailure");
//            }
//        });
//
//
//
//    }

//    private void function1(){
//        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        get_user_info info = retrofit.create(get_user_info.class);
//
//        Call<List<getUser_Info>> call = info.get_user_info(uid);
//        Log.d(TAG,"The user id is : "+uid);
//        final int finalI = i;
//        call.enqueue(new Callback<List<getUser_Info>>() {
//            @Override
//            public void onResponse(Call<List<getUser_Info>> call, Response<List<getUser_Info>> response) {
//                if(response.isSuccessful()) {
//                    Log.d(TAG,"Inside getting user info");
//                    List<getUser_Info> uinfo = response.body();
//                    getUser_Info userInfo = uinfo.get(0);
//                    user_name.add(userInfo.getUser_name());
//                    Log.d(TAG,"User Name"+user_name.get(finalI));
//                    user_tagline.add(userInfo.getUser_tag());
//                    user_image.add(userInfo.getUser_image());
//                }
//                else{
//                    Log.d(TAG,"The User does not exists");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<getUser_Info>> call, Throwable t) {
//
//                Log.d(TAG,"Inside user onFailure");
//            }
//        });
//
//    }
//



}
