package com.aws.testawsapp.Data;

import android.graphics.Bitmap;

import lombok.Data;

@Data
public class StoryPageData {
    Bitmap image;
    String title;
    String text;
    public StoryPageData(Bitmap i,String t,String tt){
        this.image=i;
        this.title=t;
        this.text=tt;

    }
    public StoryPageData(){

    }
}
