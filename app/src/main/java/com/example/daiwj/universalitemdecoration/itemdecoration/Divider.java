package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 14:02
 */
public class Divider {

    private int viewType = RecyclerView.INVALID_TYPE;

    private Drawable mDrawable;

    public Divider() {
    }

    public Divider(Drawable drawable) {
        mDrawable = drawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void draw(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            drawVertical(c, parent, child, divider, decoration);
            drawHorizontal(c, parent, child, divider, decoration);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            drawVertical(c, parent, child, divider, decoration);
            drawHorizontal(c, parent, child, divider, decoration);
        } else if (manager instanceof LinearLayoutManager) {
            if (decoration.getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                drawVertical(c, parent, child, divider, decoration);
            } else {
                drawHorizontal(c, parent, child, divider, decoration);
            }
        }
    }

    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {

    }

    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {

    }

    protected final void onDraw(Canvas c, int left, int top, int right, int bottom) {
        if (mDrawable != null) {
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    protected void getItemOffsets(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {
        if (decoration.getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            getVerticalOffset(outRect, child, parent, divider, decoration);
        } else {
            getHorizontalOffset(outRect, child, parent, divider, decoration);
        }
    }

    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {

    }

    public void getHorizontalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {

    }

    protected int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    protected boolean showLeftEdge(RecyclerView parent, View child) {
        return true;
    }

    protected boolean showTopEdge(RecyclerView parent, View child) {
        return true;
    }

    protected boolean showRightEdge(RecyclerView parent, View child) {
        return true;
    }

    protected boolean showBottomEdge(RecyclerView parent, View child) {
        return true;
    }

    protected boolean showVGap(RecyclerView parent, View child) {
        return true;
    }

    protected boolean showHGap(RecyclerView parent, View child) {
        return true;
    }

    protected boolean isFirstRow(RecyclerView parent, View child) {
        return false;
    }

    protected boolean isLastRow(RecyclerView parent, View child) {
        return false;
    }

    protected boolean isFirstColumn(RecyclerView parent, View child) {
        return false;
    }

    protected boolean isLastColumn(RecyclerView parent, View child) {
        return false;
    }
}
