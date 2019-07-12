package com.aws.testawsapp.Activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.aws.testawsapp.OnSwipeTouchListener;
import com.aws.testawsapp.R;

public class MainActivity extends TabActivity {
    TabHost tabHost;
    TabWidget tw;
    Intent i;
    int lasttab=0;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component();
    }
    public void component(){
         i=   new Intent(this, new CameraActivity().getClass());
        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        /** 새로운 탭을 추가하기 위한 TabSpect */
        TabHost.TabSpec TabSpec = tabHost.newTabSpec("tid1");
        TabHost.TabSpec Tab2Spec = tabHost.newTabSpec("tid2");
        TabHost.TabSpec Tab3Spec = tabHost.newTabSpec("tid3");
        TabHost.TabSpec Tab4Spec = tabHost.newTabSpec("tid4");
        TabHost.TabSpec Tab5Spec = tabHost.newTabSpec("tid5");

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View bv1 = li.inflate(R.layout.tabbutton1, null);
        View bv2 = li.inflate(R.layout.tabbutton2, null);
        View bv3 = li.inflate(R.layout.tabbutton3, null);
        View bv4 = li.inflate(R.layout.tabbutton4, null);
        View bv5 = li.inflate(R.layout.tabbutton5, null);
        TabSpec.setIndicator(bv1);
       Intent ii= new Intent(getApplicationContext(),
                Tab3Activity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TabSpec.setContent(new Intent(this, new StroyActivity().getClass()));
        Tab2Spec.setIndicator(bv2);
        Tab2Spec.setContent(new Intent(this, new Tab2Activity().getClass()));
        Tab3Spec.setIndicator(bv3);
        Tab3Spec.setContent(new Intent(this, new Tab3Activity().getClass()));
        Tab4Spec.setIndicator(bv4);
        Tab4Spec.setContent(new Intent(this, new Tab3Activity().getClass()));
        Tab5Spec.setIndicator(bv5);
        Tab5Spec.setContent(new Intent(this, new Tab4Activity().getClass()));


        /** 탭을 TabHost 에 추가한다 */
        tabHost.addTab(TabSpec);
        tabHost.addTab(Tab2Spec);
        tabHost.addTab(Tab3Spec);
        tabHost.addTab(Tab4Spec);
        tabHost.addTab(Tab5Spec);



        tabHost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(i,100);
            }
        });
        // 탭의 선택
        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.setOnTabChangedListener(dd);

    }
    TabHost.OnTabChangeListener dd = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {
            switch (tabId) {
                case "tid1":
                    lasttab=1;
                    break;
                case "tid2":
                    lasttab=2;
                    break;
                case "tid3":
                    //tabHost.setCurrentTabByTag("tid"+String.valueOf(lasttab));

                    break;
                case "tid4":
                    lasttab=4;
                    break;
                case "tid5":
                    lasttab=5;
                    break;

            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
