package com.aws.testawsapp.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_page_activity);
        component();
    }
    public void component(){
        nowspdlist=new ArrayList<>();

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
                int lastVisibleItemPosition=((LinearLayoutManager)rl_page_story.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemtotalcount =recyclerView.getAdapter().getItemCount();

                if(lastVisibleItemPosition==itemtotalcount){
                    Toast.makeText(getApplicationContext(),"마지막 페이지입니다.",Toast.LENGTH_LONG).show();
                    storypagedatalist= new ArrayList<>();
                    for(int i=itemtotalcount; i<itemtotalcount+10; i++){
                        int k=i%10;
                        Drawable d= getApplicationContext().getResources().getDrawable(bit[i%10]);
                        Bitmap bit = ((BitmapDrawable)d).getBitmap();
                        StoryPageData spd = new StoryPageData(bit,"test"+String.valueOf(i),String.valueOf(i)+"번째 "+"자연환경입니다");
                        spdapter.addliststory(spd);
                    }
                    spdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
