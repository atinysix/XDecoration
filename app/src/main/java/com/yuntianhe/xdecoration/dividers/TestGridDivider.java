package com.yuntianhe.xdecoration.dividers;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuntianhe.xdecoration.library.Divider;
import com.yuntianhe.xdecoration.library.GridDivider;
import com.yuntianhe.xdecoration.library.XDecoration;

/**
 * 描述:
 * 作者: daiwj on 2018/9/12 11:52
 */
public class TestGridDivider extends GridDivider {

    @Override
    protected void drawVertical(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {
//        super.drawVertical(c, parent, child, divider, decoration);
    }

    @Override
    protected void drawHorizontal(Canvas c, RecyclerView parent, View child, Divider divider, XDecoration decoration) {
//        super.drawHorizontal(c, parent, child, divider, decoration);
    }
}
