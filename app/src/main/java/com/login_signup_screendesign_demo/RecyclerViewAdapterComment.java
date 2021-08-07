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
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anuja on 8/10/17.
 */

public  class RecyclerViewAdapterComment extends RecyclerView.Adapter<RecyclerViewAdapterComment.ViewHolder> {
    private List<String> user_name,user_tagline;
    private List<Spanned> comm_list;
    private List<Integer>comm_id,comm_uid;
    private Integer aid,a_uid;
    private Context context;
    User_Info info;

    private String ans,a_user,q_tag,q_top;

    public RecyclerViewAdapterComment(Integer a_uid, Integer aid, List<Integer> comm_id, List<Integer> comm_uid, String ans, String a_user, String q_tag, String q_top, List<Spanned> comm_list, List<String> user_name, List<String> user_tagline, Context context, User_Info info) {
        this.ans=ans;
        this.a_user=a_user;
        this.q_tag=q_tag;
        this.q_top=q_top;
        this.comm_list = comm_list;
        this.user_name = user_name;
        this.user_tagline = user_tagline;
        this.comm_id=comm_id;
        this.comm_uid=comm_uid;

        this.context=context;
        this.info=info;
        this.aid=aid;
        this.a_uid=a_uid;
    }


    @Override
    public RecyclerViewAdapterComment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_comments, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapterComment.ViewHolder holder, final int position) {

        holder.tv_comm.setText( comm_list.get(position));
        holder.tv_name.setText(user_name.get(position));
        holder.tv_qual.setText(user_tagline.get(position));

//        holder.iview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,Main3Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("ANSWER",ans);
//                bundle.putString("A_NAME",a_user);
//                bundle.putString("COMMENT", String.valueOf(comm_list.get(position)));
//                bundle.putString("C_USER",user_name.get(position));
//                bundle.putString("C_TAG",user_tagline.get(position));
//                bundle.putSerializable("Com_object",info);
//                bundle.putInt("COMM ID",comm_id.get(position));
//                bundle.putInt("CUSER ID",comm_uid.get(position));
//                bundle.putInt("ANS ID",aid);
//                bundle.putInt("ANS USER ID",a_uid);
//                intent.putExtra("COMM_INFO",bundle);
//                context.startActivity(intent);
//            }
//        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Other User","Inside RecyclerViewAdpaterComment");
                Intent intent = new Intent(context,Other_user.class);
                intent.putExtra("Comm ID",comm_uid.get(position));
                intent.putExtra("CURR_UID",info.getUser_id());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return comm_list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tv_comm,tv_name,tv_qual;
        ImageView iview;
        RoundedImageView imageView;
        LinearLayout linearLayout;
        FragmentCommunication mCommunication;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_comm = (TextView) itemView.findViewById(R.id.commtext);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_qual = (TextView) itemView.findViewById(R.id.tv_qual);
            imageView = (RoundedImageView) itemView.findViewById(R.id.logo_in_answer);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.user);

        }
    }
}
