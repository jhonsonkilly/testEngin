package com.example.zhoujiawei.study3.widget.design;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

public class TestView extends View {
    private static final String TAG = "TestView";

    Paint mPaint;

    Paint mPaint2;

    Scroller mScroller;

    float mLastPointX, mLastPointY;

    int mSlop;
    private int maxWidth;
    private int minwidth;
    private int maxHeight;
    private int minHeight;
    private float moveX,moveY;

    VelocityTracker mVelocityTracker;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint2 = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.BLUE);
        mScroller = new Scroller(context);
        mSlop = ViewConfiguration.getTouchSlop();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        maxWidth = getMeasuredWidth() - 40;
        minwidth = 40;
        maxHeight = getMeasuredHeight() - 40;
        minHeight = 40;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);
        canvas.drawCircle(100, 100, 40.0f, mPaint);
        Log.d(TAG, "computeScroll1: ");
    }


    public void startScrollBy(int dx, int dy) {

        mScroller.forceFinished(true);
        int startX = getScrollX();
        int startY = getScrollY();
        mScroller.startScroll(startX, startY, dx, dy, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        /*if (mScroller.computeScrollOffset()) {
            Log.d(TAG, "computeScroll: ");
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            Log.d(TAG, "computeScroll2: "+mScroller.getCurrX()+ "  "+mScroller.getCurrY());
            //表示
            if (mScroller.getCurrX() == getScrollX()
                    && mScroller.getCurrY() == getScrollY() ) {
                postInvalidate();
            }

        } else {
            Log.d(TAG, "computeScroll is over: ");
        }*/
    }


    public void startGunDong(int dx, int dy) {
        int startX = getScrollX();
        int startY = getScrollY();
        PropertyValuesHolder xholder = PropertyValuesHolder.ofInt("scrollX", startX, startX + dx);
        PropertyValuesHolder yholder = PropertyValuesHolder.ofInt("scrollY", startY, startY + dy);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, xholder, yholder);
        animator.setDuration(1000);
        animator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if ( mVelocityTracker == null ) {
            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(event);

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                restoreTouchPoint(event);
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (event.getX() - mLastPointX);
                int deltaY = (int) (event.getY() - mLastPointY);
                if(Math.abs(deltaX) > mSlop || Math.abs(deltaY) > mSlop) {
                    scrollBy(-deltaX,-deltaY);
                    restoreTouchPoint(event);
                }
                break;


            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000,2000.0f);
                int xVelocity = (int) mVelocityTracker.getXVelocity();
                int yVelocity = (int) mVelocityTracker.getYVelocity();
                Log.d(TAG, "onTouchEvent: xVelocity:"+xVelocity+" yVelocity:"+yVelocity);
                if ( Math.abs(xVelocity) > 100
                        || Math.abs(yVelocity) > 100 ) {

                    xVelocity = Math.abs(xVelocity) > Math.abs(yVelocity) ? -xVelocity : 0;
                    yVelocity = xVelocity == 0 ? -yVelocity : 0;

                    mScroller.fling(getScrollX(),getScrollY(),
                            xVelocity,yVelocity,-1000,1000,-1000,2000);
                    invalidate();
                }
                break;

            default:
                break;
        }

        return true;

    }

    private void restoreTouchPoint(MotionEvent event) {
        mLastPointX = event.getX();
        mLastPointY = event.getY();

        Log.d(TAG, "computeScroll2: " + mLastPointX + "  " + mLastPointY);
    }


}
