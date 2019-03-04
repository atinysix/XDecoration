package com.yuntianhe.xdecoration.dividers;

import android.content.Context;

import com.yuntianhe.xdecoration.library.DefaultDividerFactory;
import com.yuntianhe.xdecoration.library.Divider;
import com.yuntianhe.xdecoration.library.GridDivider;
import com.yuntianhe.xdecoration.library.LinearDivider;
import com.yuntianhe.xdecoration.library.StaggerGridDivider;
import com.yuntianhe.xdecoration.library.XDecoration;

import java.util.List;

/**
 * 描述:
 * 作者: daiwj on 2018/9/19 13:49
 */
public class TestDividerFactory extends DefaultDividerFactory {

    public LinearDivider getDefaultLinearDivider(Context context, XDecoration decoration) {
        LinearDivider divider = new LinearDivider();
        divider.setDividerSize(40);
        return divider;
    }

    public GridDivider getDefaultGridDivider(Context context, XDecoration decoration) {
        TestGridDivider divider = new TestGridDivider();
//        divider.setDrawable(new ColorDrawable(Color.parseColor("#00ff00")));
//        divider.setDrawableWidth(30);
//        divider.setDrawableHeight(30);
        divider.setVGap(30);
        divider.setHGap(30);
        divider.setEdgeSize(45, 30, 0, 0);
        return divider;
    }

    public StaggerGridDivider getDefaultStaggerGridDivider(Context context, XDecoration decoration) {
        TestStaggerGridDivider divider = new TestStaggerGridDivider();
//        divider.setDrawable(new ColorDrawable(Color.parseColor("#00ff00")));
//        divider.setDrawableWidth(40);
//        divider.setDrawableHeight(40);
        divider.setVGap(30);
        divider.setHGap(30);
        divider.setEdgeSize(45, 0, 0, 0);
        return divider;
    }

    public List<Divider> getDividers(Context context) {
        return null;
    }
}
