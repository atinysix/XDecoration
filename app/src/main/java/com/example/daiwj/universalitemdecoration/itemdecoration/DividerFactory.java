package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 16:11
 */
public class DividerFactory {

    public LinearDivider getDefaultLinearDivider(UniversalItemDecoration decoration) {
        LinearDivider divider = new LinearDivider();
        Drawable drawable = new ColorDrawable(Color.parseColor("#ff4040"));
        divider.setDrawable(drawable);
        divider.setDividerSize(40);
        return divider;
    }

    public GridDivider getDefaultGridDivider(UniversalItemDecoration decoration) {
        GridDivider divider = new GridDivider();
        Drawable drawable = new ColorDrawable(Color.parseColor("#00ff00"));
        divider.setDrawable(drawable);
        divider.setVerticalDividerSize(50);
        divider.setHorizontalDividerSize(50);
        return divider;
    }

    public StaggerGridDivider getDefaultStaggerGridDivider(UniversalItemDecoration decoration) {
        StaggerGridDivider divider = new StaggerGridDivider();
        Drawable drawable = new ColorDrawable(Color.parseColor("#00ff00"));
        divider.setDrawable(drawable);
        divider.setVerticalDividerSize(40);
        divider.setHorizontalDividerSize(40);
        return divider;
    }
}
