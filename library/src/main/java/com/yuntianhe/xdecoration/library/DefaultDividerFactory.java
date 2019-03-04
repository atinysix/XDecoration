package com.yuntianhe.xdecoration.library;

import android.content.Context;

import java.util.List;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 16:11
 */
public class DefaultDividerFactory implements IDividerFactory {

    public LinearDivider getDefaultLinearDivider(Context context, XDecoration decoration) {
        return null;
    }

    public GridDivider getDefaultGridDivider(Context context, XDecoration decoration) {
        return null;
    }

    public StaggerGridDivider getDefaultStaggerGridDivider(Context context, XDecoration decoration) {
        return null;
    }

    public List<Divider> getDividers(Context context) {
        return null;
    }

}
