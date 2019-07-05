package com.aws.testawsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aws.testawsapp.Data.CommentData;
import com.aws.testawsapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder>{
    int commenttype;
    List<CommentData> commentDataList;
    Context context;
    public CommentAdapter(int type,List<CommentData> cdatalist,Context ctx){
        this.commenttype=type;
        this.commentDataList=cdatalist;
        this.context=ctx;
    }
    @NonNull
    @Override
    public CommentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=null;
        if(commenttype==1) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mini_comment, viewGroup, false);
        }else if(commenttype==2){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);
        }
        return new CommentAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

            CommentData cdata = commentDataList.get(position);
        if(commenttype==1) {
            holder.tv_mini_comment_name.setText(cdata.getComment_name());
            holder.tv_mini_comment_text.setText(cdata.getComment_text());
        }else if(commenttype==2){
            Glide.with(context).load(cdata.getComment_profile()).apply(RequestOptions.circleCropTransform()).into(holder.iv_comment_profile);
            holder.tv_comment_name.setText(cdata.getComment_name());
            holder.tv_comment_text.setText(cdata.getComment_text());
        }
    }




    @Override
    public int getItemCount() {
        return commentDataList.size();
    }
    //홀더를 셋팅
    public  class Holder extends RecyclerView.ViewHolder{
        ImageView iv_comment_profile;
        TextView tv_mini_comment_name,tv_mini_comment_text;
        TextView tv_comment_name,tv_comment_text;
        public Holder(@NonNull View itemView) {
            super(itemView);
            if(commenttype==1) {
                tv_mini_comment_name = (TextView) itemView.findViewById(R.id.tv_mini_comment_name);
                tv_mini_comment_text = (TextView) itemView.findViewById(R.id.tv_mini_comment_text);
            }else if(commenttype==2){
                iv_comment_profile=(ImageView)itemView.findViewById(R.id.iv_comment_profile);
                tv_comment_name = (TextView) itemView.findViewById(R.id.tv_comment_name);
                tv_comment_text = (TextView) itemView.findViewById(R.id.tv_comment_text);
            }


        }


    }
}
