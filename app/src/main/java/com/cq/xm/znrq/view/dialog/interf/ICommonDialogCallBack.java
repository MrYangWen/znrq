package com.cq.xm.znrq.view.dialog.interf;


public interface ICommonDialogCallBack {
    /**
     * 点击确认
     *
     * @param tag
     */
    public void onPositiveCase(String tag);

    /**
     * 点击取消
     *
     * @param tag
     */
    public void onNegativeCase(String tag);

}
