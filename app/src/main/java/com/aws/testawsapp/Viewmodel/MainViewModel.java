package com.aws.testawsapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;

import com.aws.testawsapp.Data.StoryPageData;

public class MainViewModel extends BaseObservable {
    @Bindable
    MutableLiveData<String> title=new MutableLiveData<>();
    @Bindable
    MutableLiveData<Bitmap> bit =new MutableLiveData<>();
    @Bindable
    MutableLiveData<String> text=new MutableLiveData<>();
    public MainViewModel(StoryPageData d){
        this.title.setValue(d.getTitle());
        this.bit.setValue(d.getImage());
        this.text.setValue(d.getText());
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }

    public void setText(MutableLiveData<String> text) {
        this.text = text;
    }

    public void setBit(MutableLiveData<Bitmap> bit) {
        this.bit = bit;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public Bitmap getBit() {
        return bit.getValue();
    }

    public MutableLiveData<String> getText() {
        return text;
    }
}
