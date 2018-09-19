package com.example.daiwj.universalitemdecoration.itemdecoration;

import android.content.Context;

import java.util.List;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 16:11
 */
public interface DividerFactory {

    LinearDivider getDefaultLinearDivider(Context context, UniversalItemDecoration decoration);

    GridDivider getDefaultGridDivider(Context context, UniversalItemDecoration decoration);

    StaggerGridDivider getDefaultStaggerGridDivider(Context context, UniversalItemDecoration decoration);

    List<Divider> getDividers(Context context);
}
