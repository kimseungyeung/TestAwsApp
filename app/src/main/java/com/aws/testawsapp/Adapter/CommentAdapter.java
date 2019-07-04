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

import org.w3c.dom.Text;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder>{
    List<CommentData> commentDataList;
    Context context;
    public CommentAdapter(List<CommentData> cdatalist,Context ctx){
        this.commentDataList=cdatalist;
        this.context=ctx;
    }
    @NonNull
    @Override
    public CommentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment,viewGroup,false);
        return new CommentAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        CommentData cdata=commentDataList.get(position);
        holder.tv_comment_name.setText(cdata.getComment_name());
        holder.tv_comment_text.setText(cdata.getComment_text());
    }




    @Override
    public int getItemCount() {
        return commentDataList.size();
    }
    //홀더를 셋팅
    public  class Holder extends RecyclerView.ViewHolder{
        ImageView iv_comment_profile;
        TextView tv_comment_name,tv_comment_text;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_comment_name=(TextView)itemView.findViewById(R.id.tv_comment_name);
            tv_comment_text=(TextView)itemView.findViewById(R.id.tv_comment_text);


        }


    }
}
