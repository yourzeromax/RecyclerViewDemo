package com.yourzeromax.recyclerviewdemo.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint, mTagPaint;
    int mTagWidth = 20;
    int mTagHeight = 40;

    public CustomDecoration() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        mTagPaint = new Paint();
        mTagPaint.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + 20;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            float right = child.getRight() - 20;
            float left = right - mTagWidth;
            float top = child.getTop();
            float bottom = child.getBottom() - 40;
            c.drawRect(left, top, right, bottom, mTagPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(20, 0, 20, 20);

        //以下设置也可以：
        //outRect.left = 20;
        // outRect.right = 20;
        //outRect.top = 0;
        // outRect.bottom = 20;
    }
}
