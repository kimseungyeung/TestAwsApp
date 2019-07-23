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
import com.aws.testawsapp.Data.FriendData;
import com.aws.testawsapp.Data.StoryData;
import com.aws.testawsapp.Data.StoryPageData;
import com.aws.testawsapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class StoryPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private ItemClick itemClick;
    Context context;
    List<StoryPageData> storyDataList;


    public StoryPageAdapter(List<StoryPageData> sdatalist, Context ctx) {
        this.storyDataList = sdatalist;
        this.context = ctx;

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.imbtn_story_collection:
                break;

        }
    }

    public interface ItemClick {
        public void onClick(View view, int position, StoryData sd);

    }

    //아이템 클릭시 실행 함수 등록 함수
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
        View view = null;
        RecyclerView.ViewHolder viewholder = null;
        /*if (viewtype == 0) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_storylist, viewGroup, false);
            return new Holder(view);
        } else {*/
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_story_page, viewGroup, false);
            return new Holder2(view);
        /*}*/
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int viewtype = getItemViewType(position);
       /* if (viewtype == 0) {

        } else if (viewtype == 1) {

        }*/
        ((Holder2)holder).iv_main_image.setImageBitmap(storyDataList.get(position).getImage());
        ((Holder2)holder).tv_story_name.setText(storyDataList.get(position).getTitle());
        ((Holder2)holder).iv_main_image.setOnClickListener(this);

    }


    @Override
    public int getItemCount() {
        return storyDataList.size() ;
    }
   public void addliststory(StoryPageData dd){

            this.storyDataList.add(dd);

   }
    public void setliststory(ArrayList<StoryPageData>dd){

            this.storyDataList=dd;
            notifyDataSetChanged();

    }
    @Override
    public int getItemViewType(int position) {
        int type = 0;

        return type;
    }

    public class Holder extends RecyclerView.ViewHolder {
        RecyclerView rl_friend;

        public Holder(@NonNull View itemView) {
            super(itemView);
            rl_friend = (RecyclerView) itemView.findViewById(R.id.rl_friend_story);
        }
    }

    //홀더를 셋팅
    public class Holder2 extends RecyclerView.ViewHolder {
        ImageView iv_main_image;
        TextView tv_story_name;

        RecyclerView rl_comment;
        //friend변수

        public Holder2(@NonNull View itemView) {
            super(itemView);
            iv_main_image=(ImageView)itemView.findViewById(R.id.iv_main_image);
            tv_story_name = (TextView) itemView.findViewById(R.id.tv_imagename);
            rl_comment = (RecyclerView) itemView.findViewById(R.id.rl_page_story);


        }

    }
}
