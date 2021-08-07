package com.login_signup_screendesign_demo;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.text.Html.fromHtml;


/**
 * A simple {@link Fragment} subclass.
 */
public class home2_screen extends Fragment {


    private String ques_txt,q_name,q_tag,q_topic,img_url;

    TextView question,tag,u_name,topic;

    private List<Spanned> ans_text;
    private List<String> user_name;
    private List<String> user_tag;
    private List<String> ans_img_url;
    private List<Integer>aid,auid;

    private int qid,q_uid;

    final private String ROOT_URL = "http://192.168.43.1/sj/";
    final private String TAG = "Answer_Screen";

    User_Info info;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    SwipeRefreshLayout swipeRefreshLayout;

    ImageView give_ans,ques_profile;

    public home2_screen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home2_screen, container, false);

        ques_txt = getArguments().getString("QUESTION");
        q_name = getArguments().getString("NAME");
        q_tag = getArguments().getString("TAGLINE");
        q_topic = getArguments().getString("TOPIC");
        img_url = getArguments().getString("IMAGE");
        info = (User_Info) getArguments().getSerializable("Com_object");
        qid = getArguments().getInt("QUESTION ID");
        q_uid = getArguments().getInt("USER ID");


            Log.d("hello","info inside home2 is :"+info);


        question = (TextView) v.findViewById(R.id.ques);
        u_name = (TextView) v.findViewById(R.id.tv_name);
        tag = (TextView) v.findViewById(R.id.tv_qual);
        topic = (TextView) v.findViewById(R.id.ques_title);
        give_ans= (ImageView) v.findViewById(R.id.ans_btn);
        ques_profile= (ImageView) v.findViewById(R.id.logo_in_homescreen);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
//                Toast.makeText(home_screen_fragment.this.getActivity(),"Tejas",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(home2_screen.this.getActivity(),"REFRESHED!",Toast.LENGTH_SHORT).show();
                        communication.respond(q_uid,qid,q_name,q_tag,ques_txt,q_topic,img_url);
//                        FragmentManager fragmentManager1 = getFragmentManager();
////                    getIntent().putExtra("Com_object",userInfo);
//                        home_screen_fragment fragment123 = new home_screen_fragment();
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("Com_object",info);
//                        fragment123.setArguments(bundle);
//                        fragmentManager1.beginTransaction().replace(R.id.content,fragment123).addToBackStack(null).commit();

                    }
                },1000);
            }
        });


        question.setText(ques_txt);
        u_name.setText(q_name);
        tag.setText(q_tag);
        topic.setText(q_topic);
        Picasso.with(getActivity().getApplicationContext()).load(ROOT_URL+img_url).into(ques_profile);


        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(home2_screen.this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        relativeLayout = (RelativeLayout) v.findViewById(R.id.hs_rel);
        linearLayout = (LinearLayout) v.findViewById(R.id.user);

        ans_text= new ArrayList<>();
        user_name= new ArrayList<>();
        user_tag= new ArrayList<>();
        ans_img_url= new ArrayList<>();
        aid=new ArrayList<>();
        auid=new ArrayList<>();


        give_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(),Answer.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("QUESTION TEXT",ques_txt);
                bundle1.putString("TOPIC NAME",q_topic);
                bundle1.putString("Q_NAME",q_name);
                bundle1.putString("Q_TAGLINE",q_tag);
                bundle1.putString("IMAGE",img_url);
//                bundle1.putSerializable("Com_object",info);
                bundle1.putInt("QUESTION ID",qid);
                bundle1.putInt("USER ID",info.getUser_id());
                bundle1.putInt("QUSER ID",q_uid);
                intent1.putExtras(bundle1);
                startActivity(intent1);

            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Other User","Inside home2screen");
                Intent intent = new Intent(getContext(),Other_user.class);
                intent.putExtra("QUSER ID",q_uid);
                intent.putExtra("CURR_UID",info.getUser_id());
                intent.putExtra("IMAGE",img_url);
                startActivity(intent);
            }
        });


        getAnswers();
        return v;
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


    private void getAnswers(){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        AnswerAPI api = retrofit.create(AnswerAPI.class);

        Call<List<Answer_model>> model = api.get_all_ans(qid);

        model.enqueue(new Callback<List<Answer_model>>() {
            @Override
            public void onResponse(Call<List<Answer_model>> call, Response<List<Answer_model>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"Inside On Response");
                    List<Answer_model> list = response.body();
                    int i = list.size()-1;
                    while(i>=0){
                        Spanned s = null;
                        if (Build.VERSION.SDK_INT >= 24) {
                            s = fromHtml(list.get(i).getAns_text(), Html.FROM_HTML_MODE_LEGACY);
                        }
                        else
                        {
                            s = fromHtml(list.get(i).getAns_text().toString());
                            Toast.makeText(getContext(),list.get(i).getAns_text().toString(), Toast.LENGTH_SHORT).show();

                        }



                        ans_text.add(s);
//                        ans_text.add(list.get(i).getAns_text());
                        user_name.add(list.get(i).getU_name());
                        user_tag.add(list.get(i).getU_tag());
                        aid.add(list.get(i).getAns_id());
                        auid.add(list.get(i).getU_id());
                        ans_img_url.add(list.get(i).getImg_path());
                        i--;
                    }

                    adapter = new RecyclerViewAdapter(img_url,ans_img_url,q_uid,qid,aid,auid,ques_txt,q_name,q_tag,q_topic,ans_text, user_name, user_tag,home2_screen.this.getActivity(),info);
                    recyclerView.setAdapter(adapter);


                }
                else {
                    Log.d(TAG,"Inside onResponse else");
                }
            }

            @Override
            public void onFailure(Call<List<Answer_model>> call, Throwable t) {
                Log.d(TAG,"Inside onFailure");
                t.printStackTrace();
            }
        });

    }

}
