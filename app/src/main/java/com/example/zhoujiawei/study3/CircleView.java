package com.example.zhoujiawei.study3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.widget.Scroller;

public class CircleView extends View {

    private Paint mPaint;

    private Scroller mScroller;

    private int minSlop;

    float mLastX, mLastY;

    public CircleView(Context context) {

        super(context);
        init(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        AccelerateInterpolator interpolator = new AccelerateInterpolator(1.2f);
        mScroller = new Scroller(context, interpolator);
        minSlop = ViewConfiguration.getTouchSlop();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(0, 0, 50, mPaint);
    }

    public void startScroll(int x, int y) {
        if (mScroller != null) {
            mScroller.forceFinished(true);
            int startX = getScrollX();
            int startY = getScrollY();
            mScroller.startScroll(startX, startY, x, y, 1000);
            invalidate();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getLastLocation(event);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) (event.getX() - mLastX);
                int moveY = (int) (event.getY() - mLastY);
                if (Math.abs(moveX) > minSlop || Math.abs(moveY) > minSlop) {
                    scrollBy(-moveX, -moveY);
                    getLastLocation(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        return true;

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            Log.i("CircleView","1");
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void getLastLocation(MotionEvent event) {
        mLastX = event.getX();
        mLastY = event.getY();
    }
}
