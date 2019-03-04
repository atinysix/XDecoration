package com.yuntianhe.xdecoration;

/**
 * 描述:
 * 作者: daiwj on 2018/8/31 11:31
 */
public class RecHeader implements Item {

    boolean hasImage;

    public RecHeader(boolean hasImage) {
        this.hasImage = hasImage;
    }

    @Override
    public int getItemViewType() {
        return TYPE_REC_HEADER;
    }
}
