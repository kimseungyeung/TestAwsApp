package com.aws.testawsapp.Activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aws.testawsapp.Adapter.StoryPageAdapter;
import com.aws.testawsapp.R;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class StoryViewActivity extends Activity {
    String title, text;
    Bitmap image;
    ImageView iv_main_image;
    TextView tv_title, tv_text;
    LinearLayout ll_main_view;
    int iid;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // supportPostponeEnterTransition();
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
 /*       getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));
        getWindow().setSharedElementExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));
        getWindow().getSharedElementEnterTransition().setDuration(1000);
        getWindow().getSharedElementReturnTransition().setDuration(1000)
                .setInterpolator(new DecelerateInterpolator());*/
       // addTransitionListener();

        setContentView(R.layout.story_view_activity);


        title = getIntent().getStringExtra("title");
        text = getIntent().getStringExtra("text");
        iid = getIntent().getIntExtra("imageid", 0);

        component();

        ViewTreeObserver observer = getWindow().getDecorView().getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                final ViewTreeObserver observer = getWindow().getDecorView().getViewTreeObserver();
                observer.removeOnPreDrawListener(this);


            //    supportStartPostponedEnterTransition();


                return true;
            }
        });

    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();

    }

    public void component() {
        ll_main_view = (LinearLayout) findViewById(R.id.ll_main_view);
        context=StoryViewActivity.this;
        iv_main_image = (ImageView) findViewById(R.id.iv_main_image);
        tv_title = (TextView) findViewById(R.id.tv_view_title);
        tv_text = (TextView) findViewById(R.id.tv_view_text);
        ViewCompat.setTransitionName(iv_main_image,title);
        tv_title.setText(title);
        tv_text.setText(text);


        /*Animation anim=AnimationUtils.loadAnimation(context,R.anim.fade_in);
        ll_main_view.setAnimation(anim);*/
        //getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));

        BitmapDrawable bd = (BitmapDrawable) context.getResources().getDrawable(iid);
        iv_main_image.setImageBitmap(bd.getBitmap());

       // iv_main_image.setTransitionName(title);


    }

    @Override
    public void onBackPressed() {
       // supportFinishAfterTransition();
        super.onBackPressed();

    }

}