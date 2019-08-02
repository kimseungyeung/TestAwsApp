package com.aws.testawsapp.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.Bindable;

import com.aws.testawsapp.Data.StoryPageData;
import com.aws.testawsapp.Repo.BaseRepo;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    Context con;

    public MutableLiveData<ArrayList<StoryPageData>> storypagedata;


    public MainViewModel(Context ctx){
        con=ctx;
    }

    public MutableLiveData<ArrayList<StoryPageData>> getStorypagedata() {
        if(storypagedata==null) {
            storypagedata = new MutableLiveData<>();
            storypagedata.setValue(new BaseRepo(con).getdata());

        }
        return storypagedata;
    }
}
