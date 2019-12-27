package com.yuntianhe.xdecoration.library;

import android.content.Context;

/**
 * 描述:
 * 作者: daiwj on 2018/9/7 16:11
 */
public interface IDividerFactory {

    LinearDivider getDefaultLinearDivider(Context context, XDecoration decoration);

    GridDivider getDefaultGridDivider(Context context, XDecoration decoration);

    StaggerGridDivider getDefaultStaggerGridDivider(Context context, XDecoration decoration);

}
