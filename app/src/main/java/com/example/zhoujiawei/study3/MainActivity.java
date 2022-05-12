package com.example.zhoujiawei.study3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zhoujiawei.study3.widget.design.TestView;
import com.example.zhoujiawei.study3.widget.design.widget.CollapsingToolbarLayout;
import com.example.zhoujiawei.study3.widget.design.widget.CoordinatorLayout;
import com.example.zhoujiawei.study3.widget.design.widget.FloatingActionButton;

import java.lang.reflect.Field;


public class MainActivity extends Activity {

    private CircleView circleView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        /*CoordinatorLayout coordinatorLayout=findViewById(R.id.main_content);
        coordinatorLayout.setStatusBarBackgroundResource(R.color.colorPrimary);*/
        /*collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorPrimaryDark));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));*/
        /*circleView = findViewById(R.id.test);
        *//*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });*//*
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //起始点和移动多少距离，注意都是反的
                circleView.startScroll(-500,-500);
            }
        });*/

    }
}
