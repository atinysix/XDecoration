package com.yuntianhe.xdecoration.library;

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

    private int mDividerSize;

    public LinearDivider() {
        super(true, true, true, true);
    }

    public LinearDivider(boolean showLeft, boolean showTop, boolean showRight, boolean showBottom) {
        super(showLeft, showTop, showRight, showBottom);
    }

    public LinearDivider(Drawable drawable, int dividerSize) {
        super(drawable);

        if (dividerSize > 0) {
            mDividerSize = dividerSize;
        }
    }

    public int getDividerSize() {
        return mDividerSize;
    }

    public void setDividerSize(int dividerSize) {
        if (dividerSize > 0) {
            mDividerSize = dividerSize;
        }
    }

    @Override
    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {

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

            if (mDividerSize > 0) {
                bottom = top + mDividerSize;
            } else {
                bottom = top + drawable.getIntrinsicHeight();
            }

            onDraw(c, left, top, right, bottom);
        }
    }

    @Override
    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {

        final Drawable drawable = getDrawable();

        if (drawable != null) {

            int left;
            int top;
            int right;
            int bottom;

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            left = child.getRight() + params.rightMargin;
            top = child.getTop() - params.topMargin;

            if (mDividerSize > 0) {
                right = left + mDividerSize;
            } else {
                right = left + drawable.getIntrinsicWidth();
            }

            bottom = parent.getBottom() + params.bottomMargin;

            onDraw(c, left, top, right, bottom);
        }
    }

    @Override
    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {
        final int dividerSize = showVGap(parent, child, decoration) ? mDividerSize : 0;

        int left = showLeftEdge(parent, child, decoration) ? mEdgeLeft : 0;
        int top = isFirstRow(parent, child, decoration) ? showTopEdge(parent, child, decoration) ? mEdgeTop : 0 : 0;
        int right = showRightEdge(parent, child, decoration) ? mEdgeRight : 0;
        int bottom = isLastRow(parent, child, decoration) ? showBottomEdge(parent, child, decoration) ? mEdgeBottom : 0 : dividerSize;
        outRect.set(left, top, right, bottom);
    }

    @Override
    public void getHorizontalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {
        final int dividerSize = showHGap(parent, child, decoration) ? mDividerSize : 0;

        int left = isFirstColumn(parent, child, decoration) ? showLeftEdge(parent, child, decoration) ? mEdgeLeft : 0 : 0;
        int top = showTopEdge(parent, child, decoration) ? mEdgeTop : 0;
        int right = isLastColumn(parent, child, decoration) ? showRightEdge(parent, child, decoration) ? mEdgeRight : 0 : dividerSize;
        int bottom = showBottomEdge(parent, child, decoration) ? mEdgeBottom : 0;
        outRect.set(left, top, right, bottom);
    }

    protected boolean isFirstRow(RecyclerView parent, View child, XDecoration decoration) {
        final int itemPosition = parent.getChildAdapterPosition(child);
        return itemPosition == 0;
    }

    protected boolean isLastRow(RecyclerView parent, View child, XDecoration decoration) {
        final int itemCount = parent.getAdapter().getItemCount();
        final int itemPosition = parent.getChildAdapterPosition(child);
        return itemPosition == itemCount - 1;
    }

    protected boolean isFirstColumn(RecyclerView parent, View child, XDecoration decoration) {
        final int itemPosition = parent.getChildAdapterPosition(child);
        return itemPosition == 0;
    }

    protected boolean isLastColumn(RecyclerView parent, View child, XDecoration decoration) {
        final int itemCount = parent.getAdapter().getItemCount();
        final int itemPosition = parent.getChildAdapterPosition(child);
        return itemPosition == itemCount - 1;
    }
}
