package com.yuntianhe.xdecoration.library;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述: 瀑布流布局的分割线，误差范围1px，如果分割线误差要求小，不建议使用
 * <p>
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

        if (showLeftEdge(parent, child, decoration)) {
            left = isFullSpan ? mEdgeLeft : computeLeft(spanCount, spanIndex, averageOffset);
        } else {
            left = 0;
        }

        if (showTopEdge(parent, child, decoration) && isFirstRow(parent, child, decoration)) {
            top = mEdgeTop;
        } else {
            top = 0;
        }

        if (showRightEdge(parent, child, decoration)) {
            right = isFullSpan ? mEdgeRight : computeRight(spanCount, spanIndex, averageOffset);
        } else {
            right = 0;
        }

        if (showBottomEdge(parent, child, decoration) && isLastRow(parent, child, decoration)) {
            bottom = mEdgeBottom;
        } else {
            bottom = showVGap(parent, child, decoration) ? mVGap : 0;
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

        if (showLeftEdge(parent, child, decoration) && isFirstColumn(parent, child, decoration)) {
            left = mEdgeLeft;
        } else {
            left = 0;
        }

        if (showTopEdge(parent, child, decoration)) {
            top = isFullSpan ? mEdgeTop : computeTop(spanCount, spanIndex, averageOffset);
        } else {
            top = 0;
        }

        if (showRightEdge(parent, child, decoration) && isLastColumn(parent, child, decoration)) {
            right = mEdgeRight;
        } else {
            right = showHGap(parent, child, decoration) ? mHGap : 0;
        }

        if (showBottomEdge(parent, child, decoration)) {
            bottom = isFullSpan ? mEdgeBottom : computeBottom(spanCount, spanIndex, averageOffset);
        } else {
            bottom = 0;
        }

        outRect.set(left, top, right, bottom);
    }

    protected boolean isEnableHeaderView() {
        return false;
    }

    protected boolean isFirstRow(RecyclerView parent, View child, XDecoration decoration) {
        final StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();

        if (isEnableHeaderView()) {
            return itemPosition == 0;
        }

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

        if (isEnableHeaderView()) {
            return itemPosition == 0;
        }

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
