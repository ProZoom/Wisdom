package com.top.wisdom.business.splash;

import android.app.Activity;

import com.top.wisdom.base.BasePresenter;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * View和Model交互的桥梁
 *
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private SplashContract.Model model;

    public SplashPresenter() {
        this.model = new SplashModel();
    }

    @Override
    public void toMainActivity(Activity activity,long delayMs) {
        model.toMainActivity(activity,delayMs);
    }

    @Override
    public void setVersion(Activity activity, AppCompatTextView tvVersion) {
        String version = model.getVersion(activity);
        tvVersion.setText(version);
    }
}
