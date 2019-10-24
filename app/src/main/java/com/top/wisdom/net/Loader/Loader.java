package com.top.wisdom.net.Loader;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.top.wisdom.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatDialog;

/**
 * 时间：2018/10/19
 * 描述：
 */
public class Loader {

    private static final int LOADING_SIZE_SCALE = 8;
    private static final int LOADING_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADINGS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    /**
     * 显示Loader
     *
     * @param context
     * @param type
     */
    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);

        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = getScreenWidth(context);
        int deviceHeight = getScreenHeight(context);
        final Window dialigWindow = dialog.getWindow();
        if (dialigWindow != null) {
            WindowManager.LayoutParams lp = dialigWindow.getAttributes();
            lp.width = deviceWidth / LOADING_SIZE_SCALE;
            lp.height = deviceHeight / LOADING_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADING_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADINGS.add(dialog);
        dialog.show();
    }


    /**
     * 显示Loader
     *
     * @param context
     * @param type
     */
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }


    /**
     * 显示Loader
     *
     * @param context
     */
    public static void showLoding(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 取消Loader
     */
    public static void dismissLoading() {
        for (AppCompatDialog dialog : LOADINGS) {
            if (dialog != null) {
                dialog.cancel();
                dialog.dismiss();
            }
        }
    }

    private static int getScreenWidth(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    private static int getScreenHeight(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
