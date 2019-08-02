package com.aws.testawsapp.Repo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.aws.testawsapp.Data.StoryPageData;
import com.aws.testawsapp.R;
import com.aws.testawsapp.Repository;

import java.util.ArrayList;

public class BaseRepo implements Repository {
    ArrayList<StoryPageData>nowspdlist;
    Context context;
    Integer[] bit={R.drawable.test1,R.drawable.test2,R.drawable.test3,R.drawable.test4,R.drawable.test5
            ,R.drawable.test6,R.drawable.test7,R.drawable.test8,R.drawable.test9,R.drawable.test10};

    public BaseRepo(Context ctx){
        context=ctx;

    }
    @Override
    public ArrayList<StoryPageData> getdata() {
        if(nowspdlist==null){
            nowspdlist=new ArrayList<>();
        }

        for(int i=0; i<10; i++){
            int k=i%10;
            Drawable d= context.getResources().getDrawable(bit[i%10]);
            Bitmap bit = ((BitmapDrawable)d).getBitmap();
            StoryPageData spd = new StoryPageData(bit,"test"+String.valueOf(i+1),String.valueOf(i+1)+"번째 "+"자연환경입니다");
            nowspdlist.add(spd);

        }
        return nowspdlist;
    }

    @Override
    public void AddData(StoryPageData sd) {
        this.nowspdlist.add(sd);
    }

    @Override
    public void SetData(ArrayList<StoryPageData> sdl) {
        this.nowspdlist=sdl;
    }
}
