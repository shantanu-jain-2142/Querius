package com.login_signup_screendesign_demo;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionProfile extends Fragment {


    private List<String> ques_list,topic_name,user_name,user_tagline,user_image;
    private List<Integer>qid,quid;

    String u_name,u_tag;

    final private String ROOT_URL = "http://192.168.43.1/sj/";
    final private String TAG = "AdapterinHome";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RelativeLayout relativeLayout;

    int userid;

    User_Info info;
    String url;

    public QuestionProfile() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_screen_rel_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(QuestionProfile.this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        relativeLayout = (RelativeLayout) v.findViewById(R.id.hs_rel);
        ques_list= new ArrayList<>();
        qid = new ArrayList<>();
        quid = new ArrayList<>();
        topic_name = new ArrayList<>();
       user_name = new ArrayList<>();
        user_tagline = new ArrayList<>();
        user_image = new ArrayList<>();

//        u_name = getArguments().getString("NAME");
//        u_tag = getArguments().getString("TAGLINE");
//        userid = getArguments().getInt("USERID");

        info = (User_Info) getArguments().getSerializable("Com_object");
        Log.d("hello","Info inside Question Profile :"+info);
        url = getArguments().getString("IMAGE");



        getQues();





        return v;
    }



    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void respond(int quid,int qid,String name, String tag, String qtxt,String t_name,String img_url) {
            home2_screen fragmentB=new home2_screen();
            Bundle bundle=new Bundle();
            bundle.putString("NAME",name);
            bundle.putString("TAGLINE",tag);
            bundle.putString("QUESTION",qtxt);
            bundle.putString("TOPIC",t_name);
            bundle.putString("IMAGE",img_url);
            bundle.putSerializable("Com_object",info);
            bundle.putInt("QUESTION ID",qid);
            bundle.putInt("QUSER ID",quid);
            fragmentB.setArguments(bundle);
            FragmentManager manager=getFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.hs_rel,fragmentB).commit();
        }
    };





    private void getQues(){

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ques_fromProfileAPI fromQues = retrofit.create(ques_fromProfileAPI.class);

        Call<List<ques_fromProfile_model>> call = fromQues.get_ques(info.getUser_id());

        call.enqueue(new Callback<List<ques_fromProfile_model>>() {
            @Override
            public void onResponse(Call<List<ques_fromProfile_model>> call, Response<List<ques_fromProfile_model>> response) {
                if(response.isSuccessful()) {

                    Log.d(TAG, "Inside onResponse");
                    List<ques_fromProfile_model> list;
                    list = response.body();
                    int i = list.size()-1;
//                    int uid = -1;
                    while (i >= 0) {
                        ques_list.add(list.get(i).getQtxt());
                        Log.d(TAG, "Ques is :" + list.get(i).getQtxt());
                        topic_name.add(list.get(i).getT_name());
                        user_name.add(info.getName());
                        user_tagline.add(info.getTagLine());
                        qid.add(list.get(i).getQid());
                        quid.add(info.getUser_id());
                        user_image.add(url);
                        i--;
                    }
                    adapter = new RecyclerAdapter(quid,qid,ques_list, user_name, user_tagline,topic_name,user_image,getChildFragmentManager(),recyclerView,communication,getContext(),info);
                    recyclerView.setAdapter(adapter);

                }
                else{
                    Log.d(TAG,"Inside onResponse else");
                }
            }

            @Override
            public void onFailure(Call<List<ques_fromProfile_model>> call, Throwable t) {

                Log.d(TAG,"Inside onFailure");
                t.printStackTrace();
            }
        });




    }


}
