package com.aws.testawsapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.aws.testawsapp.R;

public class CommentActivity extends AppCompatActivity {
    RecyclerView rl_comment_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity);
    }
    public void component(){
        rl_comment_main=(RecyclerView)findViewById(R.id.rl_comment_main);

    }
}
