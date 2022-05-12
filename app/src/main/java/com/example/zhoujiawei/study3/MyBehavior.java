package com.example.zhoujiawei.study3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewGroupUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.zhoujiawei.study3.widget.design.widget.AppBarLayout;
import com.example.zhoujiawei.study3.widget.design.widget.CoordinatorLayout;
import com.example.zhoujiawei.study3.widget.design.widget.FloatingActionButton;
import com.example.zhoujiawei.study3.widget.design.widget.NestedScrollView;


public class MyBehavior extends CoordinatorLayout.Behavior {

    private Rect mTmpRect;
    private AppBarLayout appBarLayout;
    private FloatingActionButton floatingActionButton;

    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof AppBarLayout) {
            appBarLayout = (AppBarLayout) dependency;
            floatingActionButton = (FloatingActionButton) child;

            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (Math.abs(verticalOffset) <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                        Log.i("MyBehavior",verticalOffset+"");

                        floatingActionButton.show();
                    } else {
                        // Else, we'll animate our FAB back in
                        floatingActionButton.hide();
                    }
                }
            });


        }
        return true;
    }
}
