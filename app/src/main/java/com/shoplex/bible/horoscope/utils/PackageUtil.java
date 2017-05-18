package com.shoplex.bible.horoscope.utils;

/**
 * Created by qsk on 2017/5/16.
 */

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtil {

    /**
     * 获取当前应用的版本号
     */
    public static String getVersionName() {

        // 获取packagemanager的实例
        PackageManager packageManager = MyApplication.getInstance().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }
}
