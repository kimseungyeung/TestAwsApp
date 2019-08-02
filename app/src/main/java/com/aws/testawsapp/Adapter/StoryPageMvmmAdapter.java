package com.aws.testawsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aws.testawsapp.Data.StoryPageData;
import com.aws.testawsapp.R;

import java.util.ArrayList;
import java.util.List;

public class StoryPageMvmmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private ItemClick itemClick;
    Context context;
    List<StoryPageData> storyDataList;
    int totalcount=0;
    int selectidx=0;
    public StoryPageMvmmAdapter(List<StoryPageData> sdatalist, Context ctx) {
        this.storyDataList = sdatalist;
        this.context = ctx;
        totalcount=sdatalist.size();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.iv_main_image:
                break;

        }
    }

    public interface ItemClick {
        public void onClick(View view, int position, StoryPageData sd);

    }

    //아이템 클릭시 실행 함수 등록 함수
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
        View view = null;
        RecyclerView.ViewHolder viewholder = null;
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_story_page, viewGroup, false);
            return new Holder2(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int viewtype = getItemViewType(position);

       final StoryPageData spd =storyDataList.get(position);
        ViewCompat.setTransitionName(((Holder2) holder).iv_main_image,spd.getTitle());
       // ((Holder2)holder).iv_main_image.setTransitionName(spd.getTitle());
        ((Holder2)holder).iv_main_image.setImageBitmap(spd.getImage());

        ((Holder2)holder).tv_story_name.setText(storyDataList.get(position).getTitle());


        ((Holder2)holder).iv_main_image.setOnClickListener(this);
        if((position>=totalcount-10&&totalcount!=137)||totalcount==137) {
            setAnimation(((Holder2) holder).ll_main, position);
        }
        ((Holder2)holder).iv_main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {

                    itemClick.onClick(v, position, spd);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return storyDataList.size() ;
    }
   public void addliststory(StoryPageData dd){

            this.storyDataList.add(dd);
            totalcount++;
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

        LinearLayout ll_main;
        //friend변수

        public Holder2(@NonNull View itemView) {
            super(itemView);
            iv_main_image=(ImageView)itemView.findViewById(R.id.iv_main_image);
            tv_story_name = (TextView) itemView.findViewById(R.id.tv_imagename);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main_page);


        }

    }
    private void setAnimation(View viewToAnimate, int position)
    {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.list_up_to_down);
            viewToAnimate.startAnimation(animation);
    }


}
