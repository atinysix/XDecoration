package com.yuntianhe.xdecoration;

/**
 * 描述:
 * 作者: daiwj on 2018/8/31 11:31
 */
public class Rec implements Item {
    boolean show1, show2, show3, show4;

    public Rec(boolean show1, boolean show2, boolean show3, boolean show4) {
        this.show1 = show1;
        this.show2 = show2;
        this.show3 = show3;
        this.show4 = show4;
    }

    public boolean isShow1() {
        return show1;
    }

    public void setShow1(boolean show1) {
        this.show1 = show1;
    }

    public boolean isShow2() {
        return show2;
    }

    public void setShow2(boolean show2) {
        this.show2 = show2;
    }

    public boolean isShow3() {
        return show3;
    }

    public void setShow3(boolean show3) {
        this.show3 = show3;
    }

    public boolean isShow4() {
        return show4;
    }

    public void setShow4(boolean show4) {
        this.show4 = show4;
    }

    @Override
    public int getItemViewType() {
        return TYPE_REC;
    }
}
