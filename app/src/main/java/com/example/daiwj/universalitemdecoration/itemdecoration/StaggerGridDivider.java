package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 15:51
 */
public class StaggerGridDivider extends GridDivider {

    public int getSpanCount(RecyclerView parent, View child) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        return layoutManager.getSpanCount();
    }

    public int getSpanIndex(RecyclerView parent, View itemView) {
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
        return lp.getSpanIndex();
    }

    @Override
    public boolean isSingleSpan(RecyclerView parent, View child) {
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
        return lp.isFullSpan();
    }
}
