package com.login_signup_screendesign_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shantanu on 4/10/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> user_name,user_tagline,img_url;
    private List<Integer>ans_id,ans_uid;
    private List<Spanned> ans_list;
    private Integer qid,q_uid;
    private Context context;
    User_Info info;
    final String ROOT_URL = "http://192.168.43.1/sj/";

    private String ques,q_user,q_tag,q_top,q_url;

    public RecyclerViewAdapter(String q_url, List<String> img_url, Integer q_uid, Integer qid, List<Integer> ans_id, List<Integer> ans_uid, String ques, String q_user, String q_tag, String q_top, List<Spanned> ans_list, List<String> user_name, List<String> user_tagline, Context context, User_Info info) {
        this.ques=ques;
        this.q_user=q_user;
        this.q_tag=q_tag;
        this.q_top=q_top;
        this.ans_list = ans_list;
        this.img_url = img_url;
        this.user_name = user_name;
        this.user_tagline = user_tagline;
        this.ans_id=ans_id;
        this.ans_uid=ans_uid;
        this.context=context;
        this.info=info;
        this.qid=qid;
        this.q_uid=q_uid;
        this.q_url=q_url;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_answers, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {

        holder.tv_ans.setText(ans_list.get(position));
        holder.tv_name.setText(user_name.get(position));
        holder.tv_qual.setText(user_tagline.get(position));
        Picasso.with(context.getApplicationContext()).load(ROOT_URL+img_url.get(position)).into(holder.imageView);

        holder.iview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Main3Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("QUESTION",ques);
                bundle.putString("Q_NAME",q_user);
                bundle.putString("Q_TAG",q_tag);
                bundle.putString("Q_IMAGE",q_url);
                bundle.putString("Q_Topic",q_top);
                bundle.putString("ANSWER",String.valueOf(ans_list.get(position)));
                bundle.putString("A_USER",user_name.get(position));
                bundle.putString("A_TAG",user_tagline.get(position));
                bundle.putString("IMAGE",img_url.get(position));
                bundle.putSerializable("Com_object",info);
                bundle.putInt("ANSWER ID",ans_id.get(position));
                bundle.putInt("AUSER ID",ans_uid.get(position));
                bundle.putInt("QUESTION ID",qid);
                bundle.putInt("QUSER ID",q_uid);
                intent.putExtra("ANSWER_INFO",bundle);
                context.startActivity(intent);
            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Other User","Inside RecyclerViewAdpater");
                Intent intent = new Intent(context,Other_user.class);
                intent.putExtra("QUSER ID",ans_uid.get(position));
                intent.putExtra("CURR_UID",info.getUser_id());
                intent.putExtra("IMAGE",img_url.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ans_list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tv_ans,tv_name,tv_qual;
        ImageView iview;
        RoundedImageView imageView;
        LinearLayout linearLayout;
        FragmentCommunication mCommunication;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_ans = (TextView) itemView.findViewById(R.id.answer);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_qual = (TextView) itemView.findViewById(R.id.tv_qual);
            imageView = (RoundedImageView) itemView.findViewById(R.id.logo_in_answer);
            iview = (ImageView) itemView.findViewById(R.id.btn_show_ans);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.user);

        }
    }
}
