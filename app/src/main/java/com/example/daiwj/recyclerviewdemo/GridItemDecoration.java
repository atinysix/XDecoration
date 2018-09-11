package com.example.daiwj.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/4 15:34
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private int verticalDividerWidth;
    private int horizontalDividerWidth;

    public GridItemDecoration(int verticalDividerWidth, int horizontalDividerWidth) {
        this.verticalDividerWidth = verticalDividerWidth;
        this.horizontalDividerWidth = horizontalDividerWidth;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        MainActivity.MyAdapter adapter = (MainActivity.MyAdapter) parent.getAdapter();

        final int itemPosition = parent.getChildAdapterPosition(view);

        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(parent, view);

        final int spanIndexLoop = spanIndex % spanCount;

        final int eachWidth = (spanCount - 1) * horizontalDividerWidth / spanCount;

        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        left = spanIndexLoop * (horizontalDividerWidth - eachWidth);
        right = eachWidth - left;
        bottom = verticalDividerWidth;

        final boolean isRight = spanIndexLoop == spanCount - 1;

        if (isRight) {
            right = 0;
        }

        outRect.set(left, top, right, bottom);

    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    private int getSpanIndex(RecyclerView parent, View itemView) {
        int spanIndex = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
            spanIndex = lp.getSpanIndex();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            spanIndex = lp.getSpanIndex();
        }
        return spanIndex;
    }
}
