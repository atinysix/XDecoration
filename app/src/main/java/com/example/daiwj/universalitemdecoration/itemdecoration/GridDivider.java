package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 15:51
 */
public class GridDivider extends Divider {

    protected int mDrawableWidth, mDrawableHeight;

    protected int mVGap, mHGap;

    protected int mEdgeSize = 0;

    protected int mEdgeLeft = 0;

    protected int mEdgeTop = 0;

    protected int mEdgeRight = 0;

    protected int mEdgeBottom = 0;

    public GridDivider() {

    }

    public int getDrawableWidth() {
        return mDrawableWidth;
    }

    public void setDrawableWidth(int drawableWidth) {
        this.mDrawableWidth = drawableWidth;
    }

    public int getDrawableHeight() {
        return mDrawableHeight;
    }

    public void setDrawableHeight(int drawableHeight) {
        this.mDrawableHeight = drawableHeight;
    }

    public int getVGap() {
        return mVGap;
    }

    public void setVGap(int VGap) {
        if (VGap >= 0) {
            this.mVGap = VGap;
        }
    }

    public int getHGap() {
        return mHGap;
    }

    public void setHGap(int HGap) {
        if (HGap >= 0) {
            this.mHGap = HGap;
        }
    }

    public int getEdgeSize() {
        return mEdgeSize;
    }

    public void setEdgeSize(int edgeSize) {
        if (edgeSize >= 0) {
            setEdgeSize(edgeSize, edgeSize, edgeSize, edgeSize);
        }
    }

    public void setEdgeSize(int edgeLeft, int edgeTop, int edgeRight, int edgeBottom) {
        if (edgeLeft >= 0) {
            this.mEdgeLeft = edgeLeft;
        }
        if (edgeTop >= 0) {
            this.mEdgeTop = edgeTop;
        }
        if (edgeRight >= 0) {
            this.mEdgeRight = edgeRight;
        }
        if (edgeBottom >= 0) {
            this.mEdgeBottom = edgeBottom;
        }
    }

    @Override
    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final Drawable drawable = divider.getDrawable();

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : mDrawableWidth;

        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : mDrawableHeight;

        int left = child.getLeft() - params.leftMargin;
        int right = child.getRight() + params.rightMargin + width;
        int top = child.getBottom() + params.bottomMargin;
        int bottom = top + height;

        onDraw(c, left, top, right, bottom);
    }

    @Override
    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final Drawable drawable = divider.getDrawable();

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : mDrawableWidth;

        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : mDrawableHeight;

        int left = child.getRight() + params.rightMargin;
        int right = left + width;
        int top = child.getTop() - params.topMargin;
        int bottom = child.getBottom() + params.bottomMargin + height;

        if (isFirstColumn(parent, child)) {
            left = child.getLeft() - params.leftMargin - mEdgeSize;
        }

        if (isLastColumn(parent, child)) {
            right = left + mEdgeSize;
        }

        onDraw(c, left, top, right, bottom);
    }

    @Override
    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {

        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(child);
        final int spanSize = getSpanSize(parent, child);
        final boolean isFullSpan = isFullSpan(parent, child);

        int left;
        int top;
        int right;
        int bottom;

        final int averageOffset = (mEdgeLeft + mEdgeRight + mHGap * (spanCount - 1)) / spanCount;

        left = showLeftEdge(parent, child) ? computeLeft(spanCount, spanIndex, averageOffset) : 0;
        top = 0;
        if (showRightEdge(parent, child)) {
            right = isFullSpan ? mEdgeRight : computeRight(spanCount, spanIndex + spanSize - 1, averageOffset);
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
        final int spanSize = getSpanSize(parent, child);
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
            bottom = isFullSpan ? mEdgeBottom : computeBottom(spanCount, spanIndex + spanSize - 1, averageOffset);
        } else {
            bottom = 0;
        }

        if (showLeftEdge(parent, child) && isFirstColumn(parent, child)) {
            left = mEdgeLeft;
        }

        if (showRightEdge(parent, child) && isLastColumn(parent, child)) {
            bottom = mEdgeRight;
        }

        outRect.set(left, top, right, bottom);
    }

    protected int computeLeft(int spanCount, int spanIndex, int averageOffset) {
        if (spanIndex == 0) {
            return mEdgeLeft;
        } else if (spanIndex >= spanCount / 2) {
            //从右边算起
            return averageOffset - computeRight(spanCount, spanIndex, averageOffset);
        } else {
            //从左边算起
            return mHGap - computeRight(spanCount, spanIndex - 1, averageOffset);
        }
    }

    protected int computeRight(int spanCount, int spanIndex, int averageOffset) {
        if (spanIndex == spanCount - 1) {
            return mEdgeRight;
        } else if (spanIndex >= spanCount / 2) {
            //从右边算起
            return mHGap - computeLeft(spanCount, spanIndex + 1, averageOffset);
        } else {
            //从左边算起
            return averageOffset - computeLeft(spanCount, spanIndex, averageOffset);
        }
    }

    protected int computeTop(int spanCount, int spanIndex, int averageOffset) {
        if (spanIndex == 0) {
            return mEdgeTop;
        } else if (spanIndex >= spanCount / 2) {
            //从底部算起
            return averageOffset - computeBottom(spanCount, spanIndex, averageOffset);
        } else {
            //从顶端算起
            return mVGap - computeBottom(spanCount, spanIndex - 1, averageOffset);
        }
    }

    protected int computeBottom(int spanCount, int spanIndex, int averageOffset) {
        if (spanIndex == spanCount - 1) {
            return mEdgeBottom;
        } else if (spanIndex >= spanCount / 2) {
            //从底部算起
            return mVGap - computeTop(spanCount, spanIndex + 1, averageOffset);
        } else {
            //从顶端算起
            return averageOffset - computeTop(spanCount, spanIndex, averageOffset);
        }
    }

//    protected boolean isFirstRow(RecyclerView parent, View child) {
//        final int spanCount = getSpanCount(parent);
//        final int itemPosition = parent.getChildAdapterPosition(child);
//
//        return itemPosition < spanCount;
//    }
//
//    protected boolean isLastRow(RecyclerView parent, View child) {
//        final int itemPosition = parent.getChildAdapterPosition(child);
//        final int spanCount = getSpanCount(parent);
//        final int itemCount = parent.getAdapter().getItemCount();
//
//        int totalRows = itemCount / spanCount;
//        totalRows = itemCount % spanCount == 0 ? totalRows : totalRows + 1;
//
//        int currentRow = (itemPosition + 1) / spanCount;
//        currentRow = (itemPosition + 1) % spanCount == 0 ? currentRow : currentRow + 1;
//
//        return currentRow == totalRows;
//    }

    protected boolean isFirstRow(RecyclerView parent, View child) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        return lookup.getSpanGroupIndex(itemPosition, spanCount) == lookup.getSpanGroupIndex(0, spanCount);
    }

    protected boolean isLastRow(RecyclerView parent, View child) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        return lookup.getSpanGroupIndex(itemPosition, spanCount) == lookup.getSpanGroupIndex(parent.getAdapter().getItemCount() - 1, spanCount);
    }

    protected boolean isFirstColumn(RecyclerView parent, View child) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        return itemPosition < spanCount;
    }

    protected boolean isLastColumn(RecyclerView parent, View child) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int itemCount = parent.getAdapter().getItemCount();
        final int spanCount = layoutManager.getSpanCount();
        return itemPosition > itemCount - spanCount;
    }

    protected int getSpanCount(RecyclerView parent) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        return layoutManager.getSpanCount();
    }

    protected int getSpanIndex(View child) {
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        return lp.getSpanIndex();
    }

    private int getSpanSize(RecyclerView parent, View child) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        return lookup.getSpanSize(parent.getChildAdapterPosition(child));
    }

    protected boolean isFullSpan(RecyclerView parent, View child) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int itemPosition = parent.getChildAdapterPosition(child);
        return manager.getSpanSizeLookup().getSpanSize(itemPosition) == manager.getSpanCount();
    }

}
