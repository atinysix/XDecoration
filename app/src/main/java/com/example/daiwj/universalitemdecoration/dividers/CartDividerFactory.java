package com.example.daiwj.universalitemdecoration.dividers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.daiwj.universalitemdecoration.itemdecoration.DefaultDividerFactory;
import com.example.daiwj.universalitemdecoration.itemdecoration.Divider;
import com.example.daiwj.universalitemdecoration.itemdecoration.GridDivider;
import com.example.daiwj.universalitemdecoration.itemdecoration.LinearDivider;
import com.example.daiwj.universalitemdecoration.itemdecoration.StaggerGridDivider;
import com.example.daiwj.universalitemdecoration.itemdecoration.UniversalItemDecoration;

import java.util.List;

/**
 * 描述:
 * 作者: daiwj on 2018/9/19 13:49
 */
public class CartDividerFactory extends DefaultDividerFactory {

    public LinearDivider getDefaultLinearDivider(Context context, UniversalItemDecoration decoration) {
        LinearDivider divider = new LinearDivider();
        divider.setDividerSize(40);
        return divider;
    }

    public GridDivider getDefaultGridDivider(Context context, UniversalItemDecoration decoration) {
        CartGridDivider divider = new CartGridDivider();
        divider.setDrawable(new ColorDrawable(Color.parseColor("#f2f3f4")));
        divider.setDrawableWidth(20);
        divider.setDrawableHeight(20);
        divider.setVerticalOffset(40);
        divider.setHorizontalOffset(40);
        divider.setEdgeSize(40);
        return divider;
    }

    public StaggerGridDivider getDefaultStaggerGridDivider(Context context, UniversalItemDecoration decoration) {
        CartStaggerGridDivider divider = new CartStaggerGridDivider();
        divider.setDrawable(new ColorDrawable(Color.parseColor("#00ff00")));
        divider.setDrawableWidth(40);
        divider.setDrawableHeight(40);
        divider.setVerticalOffset(40);
        divider.setHorizontalOffset(40);
        divider.setEdgeSize(30);
        return divider;
    }

    public List<Divider> getDividers(Context context) {
        return null;
    }
}
