package com.top.wisdom.business.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.top.wisdom.business.main.MainActivity;
import com.top.wisdom.utils.AppInfosUtils;

/**
 * 实际负责数据的请求，解析，过滤等数据操作
 * 处理逻辑
 */
public class SplashModel implements SplashContract.Model {

    @Override
    public void toMainActivity(Activity activity, long delayMs) {
        new Handler().postDelayed(() -> {
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        },3000);
    }

    @Override
    public String getVersion(Activity activity) {
        String packageName = AppInfosUtils.packageName(activity);
        return "Wisiom"+packageName;
    }


}
