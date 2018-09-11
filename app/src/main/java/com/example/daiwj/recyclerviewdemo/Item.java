package com.example.daiwj.recyclerviewdemo;

/**
 * 描述:
 * 作者: daiwj on 2018/8/31 11:30
 */
public interface Item {

    int TYPE_HEADER = 1;
    int TYPE_PRODUCT = 2;
    int TYPE_REC_HEADER = 3;
    int TYPE_REC = 4;

    int getViewType();
}
