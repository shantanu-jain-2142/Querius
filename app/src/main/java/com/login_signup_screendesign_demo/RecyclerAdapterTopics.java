package com.login_signup_screendesign_demo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shantanu on 24/10/17.
 */

//public class RecyclerAdapterTopics extends RecyclerView.Adapter<RecyclerAdapterTopics.ViewHolder> {
//
//    private List<String> t_list;
//    private List<Integer> t_id;
//    private Context context;
//
//    private boolean[] flag;
//
//    public RecyclerAdapterTopics(List<String> t_list, List<Integer> t_id, Context context) {
//        this.t_id=t_id;
//        this.t_list=t_list;
//        Log.d("Main5","size of t_list: "+t_list.size());
//        this.context=context;
//        flag = new boolean[t_id.size()];
//        for (int i=0;i<flag.length;i++)
//            flag[i]=false;
//    }
//
//    @Override
//    public RecyclerAdapterTopics.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.topic_selection, parent, false);
//        ViewHolder viewHolder = new ViewHolder(v);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerAdapterTopics.ViewHolder holder, final int position) {
//
//
//        holder.textView.setText(t_list.get(position));
//        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.dark_greyish));
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(flag[position]==false){
//                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.background_color));
//                    flag[position]=true;
//                }
//                else {
//                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.Red));
//                    flag[position]=false;
//                }
//            }
//        });
//
//    }
////
////    @Override
////    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
////
////    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder{
//
//        CardView cardView;
//        TextView textView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            cardView = (CardView) itemView.findViewById(R.id.card_in_topic);
//            textView = (TextView) itemView.findViewById(R.id.topic);
//        }
//    }
//}

public class RecyclerAdapterTopics extends RecyclerView.Adapter<RecyclerAdapterTopics.ViewHolder>{
    private List<String> t_list;
    private List<Integer> t_id;
    private Context context;
    boolean flag[];
    DataTransfer dataTransfer;

    public RecyclerAdapterTopics(List<String> t_list,List<Integer> t_id,Context context,DataTransfer dataTransfer) {
        this.t_id=t_id;
        this.t_list=t_list;
        Log.d("Main5","size of t_list: "+t_list.size());
        this.context=context;
        this.dataTransfer=dataTransfer;
        flag = new boolean[t_list.size()];
        for (int i=0;i<flag.length;i++)
            flag[i]=false;

    }

    @Override
    public RecyclerAdapterTopics.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topic_selection, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterTopics.ViewHolder holder, final int position) {

        holder.textView.setText(t_list.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[position]==false){
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.background_color));
                    flag[position]=true;
                }
                else {
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                    flag[position]=false;
                }
            }
        });

        dataTransfer.send(flag);

    }

    @Override
    public int getItemCount() {
        return t_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.topic);
            cardView = (CardView) itemView.findViewById(R.id.card_in_topic);
        }
    }
}