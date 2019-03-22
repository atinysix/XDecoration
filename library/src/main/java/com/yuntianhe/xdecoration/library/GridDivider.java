package com.yuntianhe.xdecoration.library;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 描述: 网格布局的分割线，误差范围1px，如果分割线误差要求小，不建议使用
 *
 * 作者: daiwj on 2018/9/7 15:51
 */
public class GridDivider extends Divider {

    protected int mDrawableWidth, mDrawableHeight;

    protected int mVGap, mHGap;

    public GridDivider() {

    }

    public int getDrawableWidth() {
        return mDrawableWidth;
    }

    public void setDrawableWidth(int drawableWidth) {
        mDrawableWidth = drawableWidth;
    }

    public int getDrawableHeight() {
        return mDrawableHeight;
    }

    public void setDrawableHeight(int drawableHeight) {
        mDrawableHeight = drawableHeight;
    }

    public int getVGap() {
        return mVGap;
    }

    public void setVGap(int VGap) {
        if (VGap >= 0) {
            mVGap = VGap;
        }
    }

    public int getHGap() {
        return mHGap;
    }

    public void setHGap(int HGap) {
        if (HGap >= 0) {
            mHGap = HGap;
        }
    }
    
    @Override
    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {
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

        if (isFirstColumn(parent, child, decoration)) {
            left = child.getLeft() - params.leftMargin - mEdgeLeft;
        }

        if (isLastColumn(parent, child, decoration)) {
            right = child.getRight() + params.rightMargin + mEdgeRight;
        }

        onDraw(c, left, top, right, bottom);

        if (isFirstRow(parent, child, decoration)) {
            top = child.getTop() - params.topMargin - mEdgeTop;
            bottom = top + mEdgeTop;
            onDraw(c, left, top, right, bottom);
        }

        if (isLastRow(parent, child, decoration)) {
            top = child.getBottom() + params.bottomMargin;
            bottom = top + mEdgeTop;
            onDraw(c, left, top, right, bottom);
        }

    }

    @Override
    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {
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

        if (isFirstColumn(parent, child, decoration)) {
            left = child.getLeft() - params.leftMargin - mEdgeLeft;
        }

        if (isLastColumn(parent, child, decoration)) {
            right = left + mEdgeRight;
        }

        onDraw(c, left, top, right, bottom);

        if (isFirstColumn(parent, child, decoration)) {
            top = child.getTop() - params.topMargin - mEdgeTop;
            bottom = top + mEdgeTop;
            onDraw(c, left, top, right, bottom);
        }

        if (isLastRow(parent, child, decoration)) {
            top = child.getBottom() + params.bottomMargin;
            bottom = top + mEdgeTop;
            onDraw(c, left, top, right, bottom);
        }
    }

    @Override
    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        final int spanSize = lookup.getSpanSize(itemPosition);
        final boolean isFullSpan = spanSize == spanCount;

        final int averageOffset = (mEdgeLeft + mEdgeRight + mHGap * (spanCount - 1)) / spanCount;

        int left;
        int top;
        int right;
        int bottom;

        left = showLeftEdge(parent, child) ? computeLeft(spanCount, spanIndex, averageOffset) : 0;
        top = 0;
        if (showRightEdge(parent, child)) {
            right = isFullSpan ? mEdgeRight : computeRight(spanCount, spanIndex + spanSize - 1, averageOffset);
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
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        final int spanSize = lookup.getSpanSize(itemPosition);
        final boolean isFullSpan = spanSize == spanCount;

        final int averageOffset = (mEdgeTop + mEdgeBottom + mVGap * (spanCount - 1)) / spanCount;

        int left;
        int top;
        int right;
        int bottom;

        left = 0;
        top = showTopEdge(parent, child) ? computeTop(spanCount, spanIndex, averageOffset) : 0;
        right = showHGap(parent, child) ? mHGap : 0;
        if (showBottomEdge(parent, child)) {
            bottom = isFullSpan ? mEdgeBottom : computeBottom(spanCount, spanIndex + spanSize - 1, averageOffset);
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

    protected final int computeLeft(int spanCount, int spanIndex, int averageOffset) {
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

    protected final int computeRight(int spanCount, int spanIndex, int averageOffset) {
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

    protected final int computeTop(int spanCount, int spanIndex, int averageOffset) {
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

    protected final int computeBottom(int spanCount, int spanIndex, int averageOffset) {
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

    protected boolean isFirstRow(RecyclerView parent, View child, XDecoration decoration) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return lookup.getSpanGroupIndex(itemPosition, spanCount) == lookup.getSpanGroupIndex(0, spanCount);
        } else {
            return spanIndex % spanCount == 0;
        }
    }

    protected boolean isLastRow(RecyclerView parent, View child, XDecoration decoration) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return lookup.getSpanGroupIndex(itemPosition, spanCount) == lookup.getSpanGroupIndex(parent.getAdapter().getItemCount() - 1, spanCount);
        } else {
            return spanIndex % spanCount == spanCount - 1;
        }
    }

    protected boolean isFirstColumn(RecyclerView parent, View child, XDecoration decoration) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return spanIndex % spanCount == 0;
        } else {
            return lookup.getSpanGroupIndex(itemPosition, spanCount) == lookup.getSpanGroupIndex(0, spanCount);
        }
    }

    protected boolean isLastColumn(RecyclerView parent, View child, XDecoration decoration) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        final int itemPosition = parent.getChildAdapterPosition(child);
        final int spanCount = layoutManager.getSpanCount();
        final int spanIndex = lp.getSpanIndex();
        if (decoration.getOrientation(parent) == XDecoration.VERTICAL) {
            return spanIndex % spanCount == spanCount - 1;
        } else {
            return lookup.getSpanGroupIndex(itemPosition, spanCount) == lookup.getSpanGroupIndex(parent.getAdapter().getItemCount() - 1, spanCount);
        }
    }
}
