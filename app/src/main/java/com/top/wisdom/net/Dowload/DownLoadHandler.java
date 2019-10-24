package com.top.wisdom.net.Dowload;

import android.content.Context;
import android.os.AsyncTask;

import com.top.wisdom.net.Callback.IError;
import com.top.wisdom.net.Callback.IFailure;
import com.top.wisdom.net.Callback.IRequest;
import com.top.wisdom.net.Callback.ISuccess;
import com.top.wisdom.net.RestClient.RestCreater;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownLoadHandler {

    private final Context mContext;
    private String mUrl;
    private Map<String, Object> mParams;

    private ISuccess iSuccess;
    private IFailure iFailure;
    private IError iError;
    private IRequest iRequest;

    //文件名
    private String mName;
    //文件拓展名（后缀）
    private String mExtension;
    //文件下载路径
    private String mDowload_dir;


    public DownLoadHandler(Context mContext,
                           String mUrl,
                           Map<String, Object> mParams,
                           IRequest iRequest,
                           ISuccess iSuccess,
                           IFailure iFailure,
                           IError iError,
                           String mName,
                           String mExtension,
                           String mDowload_dir) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mParams=mParams;
        this.iRequest=iRequest;
        this.iSuccess=iSuccess;
        this.iFailure=iFailure;
        this.iError=iError;
        this.mName = mName;
        this.mExtension = mExtension;
        this.mDowload_dir = mDowload_dir;
    }


    public final void handleDownLoad() {

        if (iRequest != null) {
            iRequest.onRequestStart();
        }

        RestCreater.getRestService().download(mUrl, mParams).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    final SaveFileTask task = new SaveFileTask(mContext,iRequest,iSuccess);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mDowload_dir, mExtension, response, mName);
                    //判断文件是否下载完成
                    if (task.isCancelled()) {
                        if (iRequest != null) {
                            iRequest.onRequestEnd();
                        }
                    }
                } else {
                    if (iError != null) {
                        iError.onError(response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (iFailure != null) {
                    iFailure.onFailure();
                }
            }
        });
    }
}
