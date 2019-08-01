package com.aws.testawsapp.Activity;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aws.testawsapp.Data.StoryPageData;
import com.aws.testawsapp.R;
import com.aws.testawsapp.viewmodel.MainViewModel;
import com.aws.testawsapp.databinding.Tab2ActivityBinding;

public class Tab2Activity extends AppCompatActivity {
    ImageView iv_test;
    ViewModel model;
    Tab2ActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.tab2_activity);
        binding = DataBindingUtil.setContentView(this, R.layout.tab2_activity);
        StoryPageData sd = new StoryPageData();
        Drawable d= getApplicationContext().getResources().getDrawable(R.drawable.test1);
        Bitmap bit = ((BitmapDrawable)d).getBitmap();
        sd.setImage(bit);
        sd.setTitle("dd");
        sd.setText("ff");
        binding.setVm(new MainViewModel(sd));

        binding.setLifecycleOwner(this);
        binding.executePendingBindings();

    }


    @BindingAdapter({"title"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

