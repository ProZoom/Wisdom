package com.top.wisdom.business.Login;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.top.wisdom.bean.BaseObjectBean;
import com.top.wisdom.bean.LoginBean;
import com.top.wisdom.net.Callback.ISuccess;
import com.top.wisdom.net.RestClient.RestClient;

import io.reactivex.Flowable;

public class LoginModel implements LoginContract.Model{


    @Override
    public Flowable<BaseObjectBean<LoginBean>> login(Context context, String username, String password) {

        RestClient.Builder()
                .withActivity(context)
                .loader()
                .url("/com/top/wisdom/login")
                .param("username",username)
                .param("password",password)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Logger.json(response);
                    }
                })
                .build()
                .post();

        return null;
    }
}
