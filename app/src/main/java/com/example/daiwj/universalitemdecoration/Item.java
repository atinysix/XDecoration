package com.example.daiwj.universalitemdecoration;

/**
 * 描述:
 * 作者: daiwj on 2018/8/31 11:30
 */
public interface Item {

    int TYPE_BIG_HEADER = 1;
    int TYPE_HEADER = 2;
    int TYPE_PRODUCT = 3;
    int TYPE_REC_HEADER = 4;
    int TYPE_REC = 5;

    int getViewType();
}
