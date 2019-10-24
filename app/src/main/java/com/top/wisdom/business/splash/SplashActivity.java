package com.top.wisdom.business.splash;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.top.wisdom.R;
import com.top.wisdom.base.BaseMvpActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.tv_version)
    AppCompatTextView tvVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        //Activity
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //AppCompatActivity
        //setContentView(R.layout.activity_splash);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        mPresenter = new SplashPresenter();
        mPresenter.attachView(this);
        mPresenter.toMainActivity(this, 3000);
        mPresenter.setVersion(this,tvVersion);
    }
}
