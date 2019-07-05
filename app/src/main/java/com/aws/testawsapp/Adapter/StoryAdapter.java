package com.aws.testawsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aws.testawsapp.Data.CommentData;
import com.aws.testawsapp.Data.StoryData;
import com.aws.testawsapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.Holder> implements View.OnClickListener {
    private ItemClick itemClick;
    Context context;
    List<StoryData> storyDataList;

    public StoryAdapter(List<StoryData> sdatalist,Context ctx){
        this.storyDataList=sdatalist;
        this.context=ctx;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imbtn_story_collection:
                break;
            case R.id.imbtn_story_comment:
                break;
            case R.id.imbtn_story_like:
                break;
            case R.id.imbtn_story_send_meessage:
                break;
            case R.id.rl_comment:
                break;
        }
    }

    public interface ItemClick {
        public void onClick(View view,int position,StoryData sd);

    }
    //아이템 클릭시 실행 함수 등록 함수
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }
    @NonNull
    @Override
    public StoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_story,viewGroup,false);
        return new StoryAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.Holder holder,final int position) {

        final StoryData sdata=storyDataList.get(position);
        Glide.with(context).load(sdata.getStory_profile()).apply(RequestOptions.circleCropTransform()).into(holder.imbtn_mini_profile);
        holder.tv_story_name.setText(sdata.getStory_name());
        List<CommentData> cdata=sdata.getCommentlist();
        CommentAdapter cadapter= new CommentAdapter(1,cdata,context);
        holder.iv_story_picture.setImageBitmap(sdata.getStory_picture());
        holder.iv_story_picture.setImageBitmap(sdata.getStory_picture());
        holder.rl_comment.setAdapter(cadapter);
        Glide.with(context).load(sdata.getStory_profile()).apply(RequestOptions.circleCropTransform()).into(holder.iv_comment_profile);
        holder.rl_comment.setLayoutManager(new LinearLayoutManager(context));
        holder.imbtn_story_like.setOnClickListener(this);
        holder.rl_comment.setOnClickListener(this);
        holder.imbtn_story_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClick != null){
                    itemClick.onClick(v,position,sdata);
                }
            }
        });
        holder.imbtn_story_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClick != null){
                    itemClick.onClick(v,position,sdata);
                }
            }
        });
        holder.imbtn_story_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClick != null){
                    itemClick.onClick(v,position,sdata);
                }
            }
        });
        holder.imbtn_story_sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClick != null){
                    itemClick.onClick(v,position,sdata);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return storyDataList.size();
    }
    //홀더를 셋팅
    public  class Holder extends RecyclerView.ViewHolder{
            ImageButton imbtn_mini_profile,imbtn_story_like,imbtn_story_comment
                    ,imbtn_story_collection,imbtn_story_sendmessage;
            TextView tv_story_name;
            ImageView iv_story_picture,iv_comment_profile;
            RecyclerView rl_comment;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imbtn_mini_profile=(ImageButton)itemView.findViewById(R.id.imbtn_mini_profile);
            tv_story_name=(TextView)itemView.findViewById(R.id.tv_story_name);
            iv_story_picture=(ImageView)itemView.findViewById(R.id.iv_story_picture);
            rl_comment =(RecyclerView)itemView.findViewById(R.id.rl_comment);
            imbtn_story_like=(ImageButton)itemView.findViewById(R.id.imbtn_story_like);
            imbtn_story_comment=(ImageButton)itemView.findViewById(R.id.imbtn_story_comment);
            imbtn_story_collection=(ImageButton)itemView.findViewById(R.id.imbtn_story_collection);
            imbtn_story_sendmessage=(ImageButton)itemView.findViewById(R.id.imbtn_story_send_meessage);
            iv_comment_profile=(ImageView) itemView.findViewById(R.id.iv_comment_profile);

        }


    }
}
