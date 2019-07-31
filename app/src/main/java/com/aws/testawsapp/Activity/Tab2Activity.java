package com.aws.testawsapp.Activity;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.aws.testawsapp.R;
import com.aws.testawsapp.ViewModel.MainViewModel;

public class Tab2Activity extends AppCompatActivity {
    ImageView iv_test;
    MainViewModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_activity);
     //   model = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    private void bind(){

    }
}
