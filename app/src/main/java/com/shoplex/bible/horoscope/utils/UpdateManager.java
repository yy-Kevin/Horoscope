package com.shoplex.bible.horoscope.utils;

import android.content.Context;

/**
 * Created by qsk on 2017/5/16.
 */

public class UpdateManager {

    Context mContext;
    //构造方法
    public UpdateManager(Context context) {
        this.mContext = context;
    }
    //检测更新
    public void checkUpdate() {
        if (isUpdate()) {
            // 显示提示对话框
            showNoticeDialog();
        }
    }
    //判断更新
    private boolean isUpdate() {
        return true;
    }
    //显示软件更新对话框
    private void showNoticeDialog() {
        //下载
        //显示下载进度
    }
    //安装
    private void installApk() {}

}
