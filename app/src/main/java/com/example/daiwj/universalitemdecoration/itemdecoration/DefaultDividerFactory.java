package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.content.Context;

import java.util.List;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 16:11
 */
public class DefaultDividerFactory implements DividerFactory {

    public LinearDivider getDefaultLinearDivider(Context context, UniversalItemDecoration decoration) {
        return null;
    }

    public GridDivider getDefaultGridDivider(Context context, UniversalItemDecoration decoration) {
        return null;
    }

    public StaggerGridDivider getDefaultStaggerGridDivider(Context context, UniversalItemDecoration decoration) {
        return null;
    }

    public List<Divider> getDividers(Context context) {
        return null;
    }

}
