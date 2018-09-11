package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 15:51
 */
public class GridDivider extends Divider {

    private int verticalDividerSize = 0;

    private int horizontalDividerSize = 0;

    public int getVerticalDividerSize() {
        return verticalDividerSize;
    }

    public void setVerticalDividerSize(int verticalDividerSize) {
        if (verticalDividerSize >= 0) {
            this.verticalDividerSize = verticalDividerSize;
        }
    }

    public int getHorizontalDividerSize() {
        return horizontalDividerSize;
    }

    public void setHorizontalDividerSize(int horizontalDividerSize) {
        if (horizontalDividerSize >= 0) {
            this.horizontalDividerSize = horizontalDividerSize;
        }
    }

    @Override
    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int left = child.getLeft() - params.leftMargin;
        int right = child.getRight() + params.rightMargin + verticalDividerSize;
        int top = child.getBottom() + params.bottomMargin;
        int bottom = top + horizontalDividerSize;

        draw(c, left, top, right, bottom);
    }

    @Override
    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, UniversalItemDecoration decoration) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int left = child.getRight() + params.rightMargin;
        int right = left + verticalDividerSize;
        int top = child.getTop() - params.topMargin;
        int bottom = child.getBottom() + params.bottomMargin + verticalDividerSize;

        draw(c, left, top, right, bottom);
    }

    @Override
    protected void getItemOffsets(Rect outRect, View child, RecyclerView parent, Divider divider, UniversalItemDecoration decoration) {
        final boolean isSingleSpan = isFullSpan(parent, child);
        final int spanCount = getSpanCount(parent, child);
        final int spanIndex = getSpanIndex(parent, child);
        final int spanIndexLoop = spanIndex % spanCount;

        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        if (decoration.getOrientation(parent) == UniversalItemDecoration.VERTICAL) {
            if (isSingleSpan) {
                left = 0;
                top = 0;
                right = 0;
                bottom = verticalDividerSize;
            } else {
                final int eachWidth = horizontalDividerSize / spanCount;

                left = spanIndexLoop * (horizontalDividerSize - eachWidth);
                top = 0;
                right = eachWidth - left;
                bottom = verticalDividerSize;

                if (isLastRow(parent.getAdapter(), spanCount, parent.getChildAdapterPosition(child))) {
                    bottom = 0;
                }

                if (isLastColumn(spanCount, spanIndex)) {
                    right = 0;
                }
            }
        } else {
            if (isSingleSpan) {
                left = 0;
                top = 0;
                right = horizontalDividerSize;
                bottom = 0;
            } else {
                final int eachWidth = verticalDividerSize / spanCount;

                left = 0;
                top = spanIndexLoop * (verticalDividerSize - eachWidth);
                right = horizontalDividerSize;
                bottom = eachWidth - top;

                if (isLastRow(parent.getAdapter(), spanCount, parent.getChildAdapterPosition(child))) {
                    right = 0;
                }

                if (isLastColumn(spanCount, spanIndex)) {
                    bottom = 0;
                }
            }
        }

        outRect.set(left, top, right, bottom);
    }

    protected boolean isLastRow(RecyclerView.Adapter adapter, int spanCount, int itemPosition) {
        final int itemCount = adapter.getItemCount();

        int totalRows = itemCount / spanCount;
        totalRows = itemCount % spanCount == 0 ? totalRows : totalRows + 1;

        int currentRow = (itemPosition + 1) / spanCount;
        currentRow = (itemPosition + 1) % spanCount == 0 ? currentRow : currentRow + 1;

        return currentRow == totalRows;
    }

    protected boolean isLastColumn(int spanCount, int spanIndex) {
        return spanIndex % spanCount == spanCount - 1;
    }

    public int getSpanCount(RecyclerView parent, View child) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        return layoutManager.getSpanCount();
    }

    public int getSpanIndex(RecyclerView parent, View child) {
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) child.getLayoutParams();
        return lp.getSpanIndex();
    }

    public boolean isFullSpan(RecyclerView parent, View child) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int itemPosition = parent.getChildAdapterPosition(child);
        return manager.getSpanSizeLookup().getSpanSize(itemPosition) == manager.getSpanCount();
    }
}
