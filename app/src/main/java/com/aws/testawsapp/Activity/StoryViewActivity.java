package com.aws.testawsapp.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.aws.testawsapp.R;

public class StoryViewActivity extends AppCompatActivity {
    String title,text;
    Bitmap image;
    ImageView iv_main_image;
    TextView tv_title,tv_text;
    int iid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_view_activity);

        title=getIntent().getStringExtra("title");
        text=getIntent().getStringExtra("text");
        iid=getIntent().getIntExtra("imageid",0);
        component();

    }
    public void component(){
        iv_main_image=(ImageView)findViewById(R.id.iv_view_image);
        iv_main_image.setTransitionName(title);
        tv_title=(TextView)findViewById(R.id.tv_view_title);
        tv_text=(TextView)findViewById(R.id.tv_view_text);
        BitmapDrawable bd= (BitmapDrawable) getApplicationContext().getResources().getDrawable(iid);
        iv_main_image.setImageBitmap(bd.getBitmap());
        tv_title.setText(title);
        tv_text.setText(text);
    }


}
