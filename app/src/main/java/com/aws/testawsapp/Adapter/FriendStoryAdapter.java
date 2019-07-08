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
import com.aws.testawsapp.Data.FriendData;
import com.aws.testawsapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FriendStoryAdapter extends RecyclerView.Adapter<FriendStoryAdapter.Holder>{

    List<FriendData> frienddatalist;
    Context context;
    public FriendStoryAdapter(List<FriendData> fdatalist, Context ctx){
        this.frienddatalist=fdatalist;
        this.context=ctx;
    }
    @NonNull
    @Override
    public FriendStoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View   view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend_story, viewGroup, false);

        return new FriendStoryAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        
            FriendData fdata = frienddatalist.get(position);
            Glide.with(context).load(fdata.getProfile()).apply(RequestOptions.circleCropTransform()).into(holder.iv_friend_profile);
            holder.tv_friend_name.setText(fdata.getName());


    }




    @Override
    public int getItemCount() {
        return frienddatalist.size();
    }
    //홀더를 셋팅
    public  class Holder extends RecyclerView.ViewHolder{
        ImageView iv_friend_profile;
        TextView tv_friend_name;

        public Holder(@NonNull View itemView) {
            super(itemView);

                iv_friend_profile=(ImageView)itemView.findViewById(R.id.iv_friend_profile);
                tv_friend_name = (TextView) itemView.findViewById(R.id.tv_friend_name);




        }


    }
}
