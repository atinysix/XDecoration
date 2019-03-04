package com.yuntianhe.xdecoration.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 14:02
 */
public class Divider {

    public static final int DEFAULT_ITEM_TYPE = RecyclerView.INVALID_TYPE;

    private int viewType = DEFAULT_ITEM_TYPE;

    protected int mEdgeSize = 0;

    protected int mEdgeLeft = 0;

    protected int mEdgeTop = 0;

    protected int mEdgeRight = 0;

    protected int mEdgeBottom = 0;

    private Drawable mDrawable;

    private boolean mShowLeft = true;

    private boolean mShowTop = true;

    private boolean mShowRight = true;

    private boolean mShowBottom = true;

    private boolean mShowVGap = true;

    private boolean mShowHGap = true;

    public Divider() {
    }

    public Divider(boolean showLeft, boolean showTop, boolean showRight, boolean showBottom) {
        mShowLeft = showLeft;
        mShowTop = showTop;
        mShowRight = showRight;
        mShowBottom = showBottom;
    }

    public Divider(Drawable drawable) {
        mDrawable = drawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public Drawable getDrawable() {
        return mDrawable;
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
            mEdgeLeft = edgeLeft;
        }
        if (edgeTop >= 0) {
            mEdgeTop = edgeTop;
        }
        if (edgeRight >= 0) {
            mEdgeRight = edgeRight;
        }
        if (edgeBottom >= 0) {
            mEdgeBottom = edgeBottom;
        }
    }

    public void draw(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            drawVertical(c, parent, child, divider, decoration);
            drawHorizontal(c, parent, child, divider, decoration);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            drawVertical(c, parent, child, divider, decoration);
            drawHorizontal(c, parent, child, divider, decoration);
        } else if (manager instanceof LinearLayoutManager) {
            if (decoration.getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                drawVertical(c, parent, child, divider, decoration);
            } else {
                drawHorizontal(c, parent, child, divider, decoration);
            }
        }
    }

    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {

    }

    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {

    }

    protected final void onDraw(Canvas c, int left, int top, int right, int bottom) {
        if (mDrawable != null) {
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    protected void getItemOffsets(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {
        if (decoration.getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            getVerticalOffset(outRect, child, parent, divider, decoration);
        } else {
            getHorizontalOffset(outRect, child, parent, divider, decoration);
        }
    }

    protected void getVerticalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {

    }

    public void getHorizontalOffset(Rect outRect, View child, RecyclerView parent, Divider divider, XDecoration decoration) {

    }

    protected boolean isFirstRow(RecyclerView parent, View child) {
        return false;
    }

    protected boolean isLastRow(RecyclerView parent, View child) {
        return false;
    }

    protected boolean isFirstColumn(RecyclerView parent, View child) {
        return false;
    }

    protected boolean isLastColumn(RecyclerView parent, View child) {
        return false;
    }

    protected boolean showLeftEdge(RecyclerView parent, View child) {
        return mShowLeft;
    }

    protected boolean showTopEdge(RecyclerView parent, View child) {
        return mShowTop;
    }

    protected boolean showRightEdge(RecyclerView parent, View child) {
        return mShowRight;
    }

    protected boolean showBottomEdge(RecyclerView parent, View child) {
        return mShowBottom;
    }

    protected boolean showVGap(RecyclerView parent, View child) {
        return mShowVGap;
    }

    protected boolean showHGap(RecyclerView parent, View child) {
        return mShowHGap;
    }

    protected int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        viewType = viewType;
    }

    public XDecoration make(Context context) {
        XDecoration decoration = new XDecoration(context);
        decoration.addDivider(this);
        return decoration;
    }
}
