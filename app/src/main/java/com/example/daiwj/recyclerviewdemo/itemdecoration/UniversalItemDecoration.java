package com.example.daiwj.recyclerviewdemo.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;

import java.util.HashMap;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 09:32
 */
public final class UniversalItemDecoration extends RecyclerView.ItemDecoration {

    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;

    private DividerFactory defaultDividerFactory = new DividerFactory();

    private final HashMap<Integer, Divider> mDividers = new HashMap<>();

    public void setDefaultDividerFactory(DividerFactory defaultDividerFactory) {
        if (defaultDividerFactory != null) {
            this.defaultDividerFactory = defaultDividerFactory;
        }
    }

    @Override
    public final void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if (parent.getLayoutManager() == null) {
            return;
        }

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutManager manager = parent.getLayoutManager();

            final int viewType = parent.getChildViewHolder(child).getItemViewType();
            Divider divider = getDivider(viewType);

            if (divider == null) {
                if (manager instanceof GridLayoutManager) {
                    divider = defaultDividerFactory.getDefaultGridDivider(this);
                } else if (manager instanceof StaggeredGridLayoutManager) {
                    divider = defaultDividerFactory.getDefaultStaggerGridDivider(this);
                } else if (manager instanceof LinearLayoutManager) {
                    divider = defaultDividerFactory.getDefaultLinearDivider(this);
                }

                if (divider != null) {
                    addDivider(divider);
                }
            }

            if (divider != null) {
                divider.dispatchDraw(c, parent, child, divider, this);
            }
        }
    }

    @Override
    public final void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getLayoutManager() == null) {
            return;
        }

        final RecyclerView.LayoutManager manager = parent.getLayoutManager();

        final int viewType = parent.getChildViewHolder(view).getItemViewType();
        Divider divider = getDivider(viewType);

        if (divider == null) {
            if (manager instanceof GridLayoutManager) {
                divider = defaultDividerFactory.getDefaultGridDivider(this);
            } else if (manager instanceof StaggeredGridLayoutManager) {
                divider = defaultDividerFactory.getDefaultStaggerGridDivider(this);
            } else if (manager instanceof LinearLayoutManager) {
                divider = defaultDividerFactory.getDefaultLinearDivider(this);
            }

            if (divider != null) {
                addDivider(divider);
            }
        }

        if (divider != null) {
            divider.getItemOffsets(outRect, view, parent, divider, this);
        }
    }

    public synchronized void addDivider(Divider divider) {
        final int viewType = divider.getViewType();
        if (!mDividers.containsKey(viewType)) {
            mDividers.put(viewType, divider);
        }
    }

    public Divider getDivider(int viewType) {
        return mDividers.get(viewType);
    }

    public int getOrientation(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager realManager = (GridLayoutManager) manager;
            return realManager.getOrientation() == GridLayoutManager.VERTICAL ? VERTICAL : HORIZONTAL;
        } else if (manager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager realManager = (StaggeredGridLayoutManager) manager;
            return realManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL ? VERTICAL : HORIZONTAL;
        } else if (manager instanceof LinearLayoutManager) {
            LinearLayoutManager realManager = (LinearLayoutManager) manager;
            return realManager.getOrientation() == LinearLayoutManager.VERTICAL ? VERTICAL : HORIZONTAL;
        } else {
            return 0;
        }
    }
}
