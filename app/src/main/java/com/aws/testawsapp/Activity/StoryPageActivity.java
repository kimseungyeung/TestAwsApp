package com.aws.testawsapp.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aws.testawsapp.Adapter.StoryPageAdapter;
import com.aws.testawsapp.Data.StoryPageData;
import com.aws.testawsapp.R;

import java.util.ArrayList;

public class StoryPageActivity extends AppCompatActivity {
    RecyclerView rl_page_story;
    ArrayList<StoryPageData> storypagedatalist;
    ArrayList<StoryPageData>nowspdlist;
    StoryPageAdapter spdapter=null;
    Integer[] bit={R.drawable.test1,R.drawable.test2,R.drawable.test3,R.drawable.test4,R.drawable.test5
            ,R.drawable.test6,R.drawable.test7,R.drawable.test8,R.drawable.test9,R.drawable.test10};
    boolean idlecheck=true;
    ProgressBar pb_loading;
    LinearLayout ll_pb;
    int limitcount=137;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_page_activity);
        component();
    }
    public void component(){
        nowspdlist=new ArrayList<>();
        pb_loading= (ProgressBar)findViewById(R.id.pb_loading);
        ll_pb=(LinearLayout)findViewById(R.id.ll_pb);
        rl_page_story=(RecyclerView)findViewById(R.id.rl_page_story);

        for(int i=0; i<10; i++){
            int k=i%10;
            Drawable d= getApplicationContext().getResources().getDrawable(bit[i%10]);
            Bitmap bit = ((BitmapDrawable)d).getBitmap();
            StoryPageData spd = new StoryPageData(bit,"test"+String.valueOf(i+1),String.valueOf(i+1)+"번째 "+"자연환경입니다");
                nowspdlist.add(spd);

        }
         spdapter=new StoryPageAdapter(nowspdlist,getApplicationContext());
        rl_page_story.setAdapter(spdapter);
        rl_page_story.setLayoutManager(new LinearLayoutManager(this));
        rl_page_story.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition=((LinearLayoutManager)rl_page_story.getLayoutManager()).findLastCompletelyVisibleItemPosition()+1;
                int itemtotalcount =rl_page_story.getAdapter().getItemCount();
                if(lastVisibleItemPosition==itemtotalcount) {
                    //new moreloadingTask().execute();
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(RecyclerView.SCROLL_STATE_SETTLING==newState){
                    idlecheck=true;
                }else{
                    idlecheck=false;
                }
            }
        });
        spdapter.setItemClick(new StoryPageAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position, StoryPageData sd) {
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(StoryPageActivity.this,view,sd.getTitle());

                Intent i =new Intent(StoryPageActivity.this,StoryViewActivity.class);
                i.putExtra("title",sd.getTitle());
                i.putExtra("text",sd.getText());
                i.putExtra("imageid",bit[position%10]);

                startActivity(i,options.toBundle());

            }
        });
    }
    Handler h = new Handler();
    public class moreloadingTask extends AsyncTask<Integer,Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ll_pb.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int lastVisibleItemPosition=((LinearLayoutManager)rl_page_story.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            int itemtotalcount =rl_page_story.getAdapter().getItemCount();
            for(int i=itemtotalcount; i<itemtotalcount+10; i++){
                    if(i!=limitcount) {
                        int k = i % 10;
                        Drawable d = getApplicationContext().getResources().getDrawable(bit[i % 10]);
                        Bitmap bit = ((BitmapDrawable) d).getBitmap();
                        StoryPageData spd = new StoryPageData(bit, "test" + String.valueOf(i + 1), String.valueOf(i + 1) + "번째 " + "자연환경입니다");
                        spdapter.addliststory(spd);

                    }else{
                        break;
                    }

            }
            if(itemtotalcount!=limitcount) {
                h.post(new Runnable() {
                    @Override
                    public void run() {

                        rl_page_story.getAdapter().notifyDataSetChanged();

                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            ll_pb.setVisibility(View.GONE);
        }
    }

}
