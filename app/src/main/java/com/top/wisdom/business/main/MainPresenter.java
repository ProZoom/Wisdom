package com.top.wisdom.business.main;



import android.app.Activity;
import android.content.Context;

import androidx.appcompat.widget.Toolbar;

import com.top.wisdom.base.BasePresenter;
import com.top.wisdom.business.splash.SplashContract;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    private MainModel model;



    @Override
    public void updateMainAdapterData(Activity activity, MainAdapter adapter) {
        model.updateMainAdapterData(activity,adapter);

    }
}
