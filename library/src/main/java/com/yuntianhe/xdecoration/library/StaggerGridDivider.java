package com.yuntianhe.xdecoration.library;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 15:51
 */
public class StaggerGridDivider extends GridDivider {

    @Override
    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {
        final StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        final boolean isFullSpan = lp.isFullSpan();

        final int averageOffset = (mEdgeLeft + mEdgeRight + mHGap * (spanCount - 1)) / spanCount;

        int left;
        int top;
        int right;
        int bottom;

        left = showLeftEdge(parent, child) ? computeLeft(spanCount, spanIndex, averageOffset) : 0;
        top = 0;
        if (showRightEdge(parent, child)) {
            right = isFullSpan ? mEdgeRight : computeRight(spanCount, spanIndex, averageOffset);
        } else {
            right = 0;
        }
        bottom = showVGap(parent, child) ? mVGap : 0;

        if (showTopEdge(parent, child) && isFirstRow(parent, child, decoration)) {
            top = mEdgeTop;
        }

        if (showBottomEdge(parent, child) && isLastRow(parent, child, decoration)) {
            bottom = mEdgeBottom;
        }

        outRect.set(left, top, right, bottom);
    }

    @Override
    public void getHorizontalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {
        final StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        final boolean isFullSpan = lp.isFullSpan();

        final int averageOffset = (mEdgeTop + mEdgeBottom + mVGap * (spanCount - 1)) / spanCount;

        int left;
        int top;
        int right;
        int bottom;

        left = 0;
        top = showTopEdge(parent, child) ? computeTop(spanCount, spanIndex, averageOffset) : 0;
        right = showHGap(parent, child) ? mHGap : 0;
        if (showBottomEdge(parent, child)) {
            bottom = isFullSpan ? mEdgeBottom : computeBottom(spanCount, spanIndex, averageOffset);
        } else {
            bottom = 0;
        }

        if (showLeftEdge(parent, child) && isFirstColumn(parent, child, decoration)) {
            left = mEdgeLeft;
        }

        if (showRightEdge(parent, child) && isLastColumn(parent, child, decoration)) {
            right = mEdgeRight;
        }

        outRect.set(left, top, right, bottom);
    }

    protected boolean isFirstRow(RecyclerView parent, View child, XDecoration decoration) {
        final StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return itemPosition < spanCount;
        } else {
            return spanIndex % spanCount == 0;
        }
    }

    protected boolean isLastRow(RecyclerView parent, View child, XDecoration decoration) {
        final StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int itemCount = parent.getAdapter().getItemCount();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return itemPosition > itemCount - spanCount;
        } else {
            return spanIndex % spanCount == spanCount - 1;
        }
    }

    protected boolean isFirstColumn(RecyclerView parent, View child, XDecoration decoration) {
        final StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return spanIndex % spanCount == 0;
        } else {
            return itemPosition < spanCount;
        }
    }

    protected boolean isLastColumn(RecyclerView parent, View child, XDecoration decoration) {
        final StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int itemCount = parent.getAdapter().getItemCount();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return spanIndex % spanCount == spanCount - 1;
        } else {
            return itemPosition > itemCount - spanCount;
        }
    }
}
