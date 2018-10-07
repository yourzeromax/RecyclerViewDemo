package com.yourzeromax.recyclerviewdemo.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yourzeromax.recyclerviewdemo.Bean.GroupInfo;

public class StickyDecoration extends RecyclerView.ItemDecoration {
    CallBack mCallback;
    int mHeaderHeight = 50;
    int mDividerHeight = 2;

    Paint mPaint, mTextPaint;

    public StickyDecoration(CallBack mCallback) {
        this.mCallback = mCallback;
        mPaint = new Paint();
        mTextPaint = new Paint();
        mPaint.setColor(Color.CYAN);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(20);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

//        int childCount = parent.getChildCount();
//
//        for (int i = 0; i < childCount; i++) {
//            View view = parent.getChildAt(i);
//            int index = parent.getChildAdapterPosition(view);
//            if (mCallback != null) {
//                GroupInfo groupinfo = mCallback.getGroupInfo(index);
//                //只有组内的第一个ItemView之上才绘制
//                if (groupinfo.isFirstInGroup()) {
//                    int left = parent.getPaddingLeft();
//                    int top = view.getTop() - mHeaderHeight;
//                    int right = parent.getWidth() - parent.getPaddingRight();
//                    int bottom = view.getTop();
//                    //绘制Header
//                    c.drawRect(left, top, right, bottom, mPaint);
//
//                    float titleX = left + 20;
//                    float titleY = bottom - 20;
//                    //绘制Title
//                    c.drawText(groupinfo.getTitle(), titleX, titleY, mTextPaint);
//                }
//            }
//        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            if (mCallback != null) {
                GroupInfo groupinfo = mCallback.getGroupInfo(index);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                //屏幕上第一个可见的 ItemView 时，i == 0;
                if (i != 0) {
                    //只有组内的第一个ItemView之上才绘制
                    if (groupinfo.isFirstInGroup()) {
                        int top = view.getTop() - mHeaderHeight;
                        int bottom = view.getTop();
                        drawHeaderRect(c, groupinfo, left, top, right, bottom);
                    }
                } else {
                    //如果是屏幕上的第一个View，则必须绘制Header，如果是当前组内的最后一个View，则它的Header需要进行特殊处理
                    int top = parent.getPaddingTop();
                    if (groupinfo.isLastInGroup()) {
                        int suggestTop = view.getBottom() - mHeaderHeight;
                        // 当 ItemView 与 Header 底部平齐的时候，判断 Header 的顶部是否小于
                        // parent 顶部内容开始的位置，如果小于则对 Header.top 进行位置更新，
                        //否则将继续保持吸附在 parent 的顶部
                        if (suggestTop < top) {
                            top = suggestTop;
                        }
                    }
                    int bottom = top + mHeaderHeight;
                    drawHeaderRect(c, groupinfo, left, top, right, bottom);
                }

            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);

        if (mCallback != null) {
            GroupInfo groupInfo = mCallback.getGroupInfo(position);

            //如果是组内的第一个,则需要预留一个Header的高度，否则预留分割线的高度
            if (groupInfo != null && groupInfo.isFirstInGroup()) {
                outRect.top = mHeaderHeight;
            } else {
                outRect.top = mDividerHeight;
            }
        }
    }

    private void drawHeaderRect(Canvas c, GroupInfo groupinfo, int left, int top, int right, int bottom) {
        //绘制Header
        c.drawRect(left, top, right, bottom, mPaint);

        float titleX = left + 20;
        float titleY = bottom - 20;
        //绘制Title
        c.drawText(groupinfo.getTitle(), titleX, titleY, mTextPaint);
    }

    public interface CallBack {
        GroupInfo getGroupInfo(int position);
    }
}
