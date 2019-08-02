package com.aws.testawsapp;

import com.aws.testawsapp.Data.StoryPageData;

import java.util.ArrayList;

public interface Repository {
    public ArrayList<StoryPageData> getdata();

    public void AddData(StoryPageData sd);

    public void SetData(ArrayList<StoryPageData> sdl);
}
