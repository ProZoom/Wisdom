package com.top.wisdom.business.Login;

import android.content.Context;

import com.top.wisdom.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginContract.Model model;

    public LoginPresenter() {
        this.model = new LoginModel();
    }


    @Override
    public void login(Context context, String username, String password) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        //登陆
        model.login(context,username, password);
    }
}
