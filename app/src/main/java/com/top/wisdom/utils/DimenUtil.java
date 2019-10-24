package com.top.wisdom.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;


/**
 * 作者：ProZoom
 * 时间：2018/10/19
 * 描述：
 */
public class DimenUtil {

    public static int getScreenWidth(Context context){
        final Resources resources=context.getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();

        return dm.widthPixels;
    }


    public static int getScreenHeight(Context context){
        final Resources resources=context.getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();

        return dm.heightPixels;
    }
}
