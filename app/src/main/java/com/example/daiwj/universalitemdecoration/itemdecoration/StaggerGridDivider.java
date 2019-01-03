package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 15:51
 */
public class StaggerGridDivider extends GridDivider {

    @Override
    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {
        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(child);
        final boolean isFullSpan = isFullSpan(parent, child);

        int left;
        int top;
        int right;
        int bottom;

        final int averageOffset = (mEdgeLeft + mEdgeRight + mHGap * (spanCount - 1)) / spanCount;

        left = showLeftEdge(parent, child) ? computeLeft(spanCount, spanIndex, averageOffset) : 0;
        top = 0;
        if (showRightEdge(parent, child)) {
            right = isFullSpan ? mEdgeRight : computeRight(spanCount,spanIndex, averageOffset);
        } else {
            right = 0;
        }
        bottom = showVGap(parent, child) ? mVGap : 0;

        if (showTopEdge(parent, child) && isFirstRow(parent, child)) {
            top = mEdgeTop;
        }

        if (showBottomEdge(parent, child) && isLastRow(parent, child)) {
            bottom = mEdgeBottom;
        }

        outRect.set(left, top, right, bottom);
    }

    @Override
    public void getHorizontalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {
        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(child);
        final boolean isFullSpan = isFullSpan(parent, child);

        int left;
        int top;
        int right;
        int bottom;

        final int averageOffset = (mEdgeTop + mEdgeBottom + mVGap * (spanCount - 1)) / spanCount;

        left = 0;
        top = showTopEdge(parent, child) ? computeTop(spanCount, spanIndex, averageOffset) : 0;
        right = showHGap(parent, child) ? mHGap : 0;
        if (showBottomEdge(parent, child)) {
            bottom = isFullSpan ? mEdgeBottom : computeBottom(spanCount, spanIndex, averageOffset);
        } else {
            bottom = 0;
        }

        if (showLeftEdge(parent, child) && isFirstColumn(parent, child)) {
            left = mEdgeLeft;
        }

        if (showRightEdge(parent, child) && isLastColumn(parent, child)) {
            right = mEdgeRight;
        }

        outRect.set(left, top, right, bottom);
    }

    protected boolean isFirstRow(RecyclerView parent, View child) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        return itemPosition < spanCount;
    }

    protected boolean isLastRow(RecyclerView parent, View child) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int itemCount = parent.getAdapter().getItemCount();
        final int spanCount = layoutManager.getSpanCount();
        return itemPosition > itemCount - spanCount;
    }

    protected boolean isFirstColumn(RecyclerView parent, View child) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        return itemPosition < spanCount;
    }

    protected boolean isLastColumn(RecyclerView parent, View child) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int itemCount = parent.getAdapter().getItemCount();
        final int spanCount = layoutManager.getSpanCount();
        return itemPosition > itemCount - spanCount;
    }

    public int getSpanCount(RecyclerView parent) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        return layoutManager.getSpanCount();
    }

    public int getSpanIndex(View child) {
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        return lp.getSpanIndex();
    }

    @Override
    public boolean isFullSpan(RecyclerView parent, View child) {
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        return lp.isFullSpan();
    }
}
