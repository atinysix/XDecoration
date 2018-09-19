package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 15:51
 */
public class GridDivider extends Divider {

    private int drawableWidth, drawableHeight;

    private int verticalOffset, horizontalOffset;

    private int edgeSize = 0;

    public GridDivider() {
    }

    public int getDrawableWidth() {
        return drawableWidth;
    }

    public void setDrawableWidth(int drawableWidth) {
        this.drawableWidth = drawableWidth;
    }

    public int getDrawableHeight() {
        return drawableHeight;
    }

    public void setDrawableHeight(int drawableHeight) {
        this.drawableHeight = drawableHeight;
    }

    public int getVerticalOffset() {
        return verticalOffset;
    }

    public void setVerticalOffset(int verticalOffset) {
        if (verticalOffset > 0) {
            this.verticalOffset = verticalOffset;
        }
    }

    public int getHorizontalOffset() {
        return horizontalOffset;
    }

    public void setHorizontalOffset(int horizontalOffset) {
        if (horizontalOffset >= 0) {
            this.horizontalOffset = horizontalOffset;
        }
    }

    public int getEdgeSize() {
        return edgeSize;
    }

    public void setEdgeSize(int edgeSize) {
        if (edgeSize > 0) {
            this.edgeSize = edgeSize;
        }
    }

    @Override
    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final Drawable drawable = divider.getDrawable();

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : drawableWidth;

        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : drawableHeight;

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

        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(child);

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : drawableWidth;

        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : drawableHeight;

        int left = child.getRight() + params.rightMargin;
        int right = left + width;
        int top = child.getTop() - params.topMargin;
        int bottom = child.getBottom() + params.bottomMargin + height;

        if (isFirstColumn(spanCount, spanIndex)) {
            left = child.getLeft() - params.leftMargin - edgeSize;
        }

        if (isLastColumn(spanCount, spanIndex)) {
            right = left + edgeSize;
        }

        onDraw(c, left, top, right, bottom);
    }

    @Override
    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {
        final boolean isSingleSpan = isFullSpan(parent, child);
        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(child);
        final int spanIndexLoop = spanIndex % spanCount;

        int left;
        int top;
        int right;
        int bottom;

        if (isSingleSpan) {
            left = 0;
            top = 0;
            right = 0;
            bottom = verticalOffset;
        } else {
            final int eachWidth = horizontalOffset / spanCount;

            left = spanIndexLoop * (horizontalOffset - eachWidth);
            top = 0;
            right = eachWidth - left;
            bottom = verticalOffset;

            if (isFirstColumn(spanCount, spanIndex)) {
                left = edgeSize;
            }

//            if (isLastRow(parent, parent.getChildAdapterPosition(child))) {
//                bottom = 0;
//            }

            if (isLastColumn(spanCount, spanIndex)) {
                right = edgeSize;
            }
        }

        outRect.set(left, top, right, bottom);
    }

    @Override
    public void getHorizontalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {
        final boolean isFullSpan = isFullSpan(parent, child);
        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(child);
        final int spanIndexLoop = spanIndex % spanCount;

        int left;
        int top;
        int right;
        int bottom;

        if (isFullSpan) {
            left = 0;
            top = 0;
            right = horizontalOffset;
            bottom = 0;
        } else {
            final int eachWidth = verticalOffset / spanCount;

            left = 0;
            top = spanIndexLoop * (verticalOffset - eachWidth);
            right = horizontalOffset;
            bottom = eachWidth - top;

            if (isLastRow(parent, parent.getChildAdapterPosition(child))) {
                right = 0;
            }

            if (isLastColumn(spanCount, spanIndex)) {
                bottom = 0;
            }
        }

        outRect.set(left, top, right, bottom);
    }

    protected boolean isFirstRow(RecyclerView parent, int itemPosition) {
        final int spanCount = getSpanCount(parent);

        int currentRow = (itemPosition + 1) / spanCount;
        currentRow = (itemPosition + 1) % spanCount == 0 ? currentRow : currentRow + 1;

        return currentRow == 0;
    }

    protected boolean isLastRow(RecyclerView parent, int itemPosition) {
        final int spanCount = getSpanCount(parent);
        final int itemCount = parent.getAdapter().getItemCount();

        int totalRows = itemCount / spanCount;
        totalRows = itemCount % spanCount == 0 ? totalRows : totalRows + 1;

        int currentRow = (itemPosition + 1) / spanCount;
        currentRow = (itemPosition + 1) % spanCount == 0 ? currentRow : currentRow + 1;

        return currentRow == totalRows;
    }

    protected boolean isFirstColumn(int spanCount, int spanIndex) {
        return spanIndex % spanCount == 0;
    }

    protected boolean isLastColumn(int spanCount, int spanIndex) {
        return spanIndex % spanCount == spanCount - 1;
    }

    public int getSpanCount(RecyclerView parent) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        return layoutManager.getSpanCount();
    }

    public int getSpanIndex(View child) {
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        return lp.getSpanIndex();
    }

    public boolean isFullSpan(RecyclerView parent, View child) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int itemPosition = parent.getChildAdapterPosition(child);
        return manager.getSpanSizeLookup().getSpanSize(itemPosition) == manager.getSpanCount();
    }
}
