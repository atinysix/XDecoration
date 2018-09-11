package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 15:50
 */
public class LinearDivider extends Divider {

    private int dividerSize;

    public int getDividerSize() {
        return dividerSize;
    }

    public void setDividerSize(int dividerSize) {
        this.dividerSize = dividerSize;
    }

    Rect mBounds = new Rect();

    @Override
    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {

        final Drawable drawable = getDrawable();

        int left;
        int top;
        int right;
        int bottom;

        if (drawable != null) {

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            left = child.getLeft() - params.leftMargin;
            right = child.getRight() + params.rightMargin;
            top = child.getBottom() + params.bottomMargin;

            if (dividerSize >= 0) {
                bottom = top + dividerSize;
            } else {
                bottom = top + drawable.getIntrinsicHeight();
            }

            draw(c, left, top, right, bottom);
        }
    }

    @Override
    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {

        final Drawable drawable = getDrawable();

        if (drawable != null) {

            int left;
            int top;
            int right;
            int bottom;

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            left = child.getRight() + params.rightMargin;
            top = child.getTop() - params.topMargin;

            if (dividerSize >= 0) {
                right = left + dividerSize;
            } else {
                right = left + drawable.getIntrinsicWidth();
            }

            bottom = parent.getBottom() + params.bottomMargin;

            draw(c, left, top, right, bottom);
        }
    }

    @Override
    protected void getItemOffsets(Rect outRect, View view, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {
        if (decoration.getOrientation(parent) == UniversalItemDecoration.VERTICAL) {
            outRect.set(0, 0, 0, dividerSize);
        } else {
            outRect.set(0, 0, dividerSize, 0);
        }
    }
}
