package com.top.wisdom.net.Callback;

/**
 * 作者：ProZoom
 * 时间：2018/10/19
 * 描述：
 */
public interface IRerofitCallBack {

    void onSuccess(String response);
    void onFail();
    void onError(int code, String msg);
    void onRequestStart();
    void onRequestEnd();

}
