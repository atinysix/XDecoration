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

    private Drawable drawable;

    public Divider() {
    }

    public Divider(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
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

    protected void getItemOffsets(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {

    }

    protected final void onDraw(Canvas c, int left, int top, int right, int bottom) {
        if (drawable != null) {
            drawable.setBounds(left, top, right, bottom);
        }
        drawable.draw(c);
    }

    protected boolean isLastRow(RecyclerView.Adapter adapter, int spanCount, int itemPosition) {
        return false;
    }

    protected boolean isLastColumn(int spanCount, int spanIndex) {
        return false;
    }

    protected int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
