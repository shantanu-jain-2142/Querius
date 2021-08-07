package com.login_signup_screendesign_demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shantanu on 1/10/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private List<String> ques_list,user_name,user_tagline,topic_name,img_url;

//    private List<Integer> user_id;
//
//    private List<get_detailed_fromQues> list;

    private List<String> user_image;

    private List<Integer>ques_id,user_id;
    private FragmentManager manager;


    RecyclerView recyclerView;

    FragmentCommunication communication;

    Context context;

    final String ROOT_URL = "http://192.168.43.1/sj/";

    User_Info info;


//    final private String ROOT_URL = "https://wwwqueriuscom.000webhostapp.com/";
//    final private String TAG = "Adapter";


    public RecyclerAdapter(List<Integer> user_id,List<Integer> ques_id,List<String> ques_list, List<String> user_name, List<String> user_tagline, List<String> topic_name, List<String> img_url,FragmentManager manager,RecyclerView recyclerView,FragmentCommunication communication,Context context,User_Info info) {
        this.ques_list = ques_list;
        this.user_name = user_name;
        this.user_tagline = user_tagline;
//        this.user_image = user_image;
        this.topic_name = topic_name;
        this.img_url=img_url;
        this.manager = manager;
        this.ques_id=ques_id;
        this.user_id=user_id;
        this.recyclerView=recyclerView;
        this.communication=communication;
        this.context=context;
        this.info=info;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_screen_layout, parent, false);


//
//        getQues();
//
//        get_user_info();

        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_ques.setText(ques_list.get(position));
        holder.tv_name.setText(user_name.get(position));
        holder.tv_qual.setText(user_tagline.get(position));
        holder.tv_top.setText(topic_name.get(position));
        Picasso.with(context.getApplicationContext()).load(ROOT_URL+img_url.get(position)).into(holder.imageView);

        holder.iview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG","Inside onCLick");

                recyclerView.setVisibility(View.GONE);
//                manager.beginTransaction().replace(R.id.hs_rel,new home2_screen()).commit();
                home2_screen fragment = new home2_screen();
                Bundle bundle = new Bundle();
                bundle.putString("NAME",user_name.get(position));
                bundle.putString("TAGLINE",user_tagline.get(position));
                bundle.putString("QUESTION",ques_list.get(position));
                bundle.putString("TOPIC",topic_name.get(position));
                bundle.putString("IMAGE",img_url.get(position));
                bundle.putInt("QUESTION ID",ques_id.get(position));
                bundle.putInt("USER ID",user_id.get(position));
                fragment.setArguments(bundle);

                holder.mCommunication.respond(user_id.get(position),ques_id.get(position),user_name.get(position),user_tagline.get(position),ques_list.get(position),topic_name.get(position),img_url.get(position));


            }
        });

        holder.give_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context,Answer.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("QUESTION TEXT",ques_list.get(position));
                bundle1.putString("TOPIC NAME",topic_name.get(position));
                bundle1.putString("IMAGE",img_url.get(position));
                bundle1.putString("Q_NAME",user_name.get(position));
                bundle1.putString("Q_TAGLINE",user_tagline.get(position));
//                bundle1.putSerializable("Com_object",info);
                bundle1.putInt("QUESTION ID",ques_id.get(position));
                bundle1.putInt("USER ID",info.getUser_id());
                Log.d("hello","inisde recycler :"+ques_id.get(position));
//                bundle1.putInt("USER ID",user_id.get(position));
//                bundle1.putSerializable("Com_object",info);
                intent1.putExtras(bundle1);
                context.startActivity(intent1);

            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Other User","Inside RecyclerAdpater");
                Intent intent = new Intent(context,Other_user.class);
                intent.putExtra("QUSER ID",user_id.get(position));
                intent.putExtra("CURR_UID",info.getUser_id());
                intent.putExtra("IMAGE",img_url.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ques_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tv_ques,tv_name,tv_qual,tv_top;
        ImageView iview,give_ans;
        RoundedImageView imageView;
        RelativeLayout relativeLayout;
        FragmentCommunication mCommunication;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_ques = (TextView) itemView.findViewById(R.id.ques);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_qual = (TextView) itemView.findViewById(R.id.tv_qual);
            imageView = (RoundedImageView) itemView.findViewById(R.id.logo_in_homescreen);
            tv_top = (TextView) itemView.findViewById(R.id.ques_title);
            iview = (ImageView) itemView.findViewById(R.id.btn_answers);
            give_ans = (ImageView) itemView.findViewById(R.id.ans_btn);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rel_main);
            mCommunication = communication;
            linearLayout = (LinearLayout) itemView.findViewById(R.id.user);
        }
    }


//    private void getQues(){
//
//        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        get_detailed_fromQuesAPI fromQues = retrofit.create(get_detailed_fromQuesAPI.class);
//
//        Call<List<get_detailed_fromQues>> call = fromQues.getQues();
//
//        call.enqueue(new Callback<List<get_detailed_fromQues>>() {
//            @Override
//            public void onResponse(Call<List<get_detailed_fromQues>> call, Response<List<get_detailed_fromQues>> response) {
//                    if(response.isSuccessful()){
//
//                        Log.d(TAG,"Inside onResponse");
//                        list = response.body();
//                        int i=0;
//                        while(i<list.size()){
//                            ques_list.add(list.get(i).getQues_text());
//                            Log.d(TAG,"Ques is :"+list.get(i).getQues_text());
//                            user_id.add(list.get(i).getUser_id());
//                            i++;
//                        }
//
//                    }
//                    else{
//                        Log.d(TAG,"Inside onResponse else");
//                    }
//            }
//
//            @Override
//            public void onFailure(Call<List<get_detailed_fromQues>> call, Throwable t) {
//
//                Log.d(TAG,"Inside onFailure");
//                t.printStackTrace();
//            }
//        });
//
//    }
//
//    private void get_user_info(){
//
//        int i=0,uid=-1;
//
//
//        while (i<user_id.size()){
//
//            uid = user_id.get(i);
//            final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
//            get_user_info info = retrofit.create(get_user_info.class);
//
//            Call<List<User_Info>> call = info.get_user_info(uid);
//
//            final int finalI = i;
//            call.enqueue(new Callback<List<User_Info>>() {
//                @Override
//                public void onResponse(Call<List<User_Info>> call, Response<List<User_Info>> response) {
//                    if(response.isSuccessful()) {
//                        Log.d(TAG,"Inside getting user info");
//                        List<User_Info> uinfo = response.body();
//                        User_Info userInfo = uinfo.get(0);
//                        user_name.add(userInfo.getName());
//                        Log.d(TAG,"User Name"+user_name.get(finalI));
//                        user_tagline.add(userInfo.getTagLine());
//                        user_image.add(userInfo.getImageURL());
//                    }
//                    else{
//                        Log.d(TAG,"The User does not exists");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<User_Info>> call, Throwable t) {
//
//                    Log.d(TAG,"Inside user onFailure");
//                }
//            });
//            i++;
//        }
//    }

}
