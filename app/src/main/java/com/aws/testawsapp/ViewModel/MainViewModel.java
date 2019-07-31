package com.aws.testawsapp.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.aws.testawsapp.Data.StoryPageData;

import java.util.Observable;
import java.util.Optional;

import lombok.Data;


public class MainViewModel extends BaseObservable {
    private ObservableField<String> title;

    public void settitle(String title) {
        this.title.set(title);

    }
    @Bindable
    public String getStoryPageData() {
        return this.title.get();
    }
}
