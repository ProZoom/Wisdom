package com.top.wisdom.net.Builder;

import android.content.Context;

import com.top.wisdom.net.Callback.IError;
import com.top.wisdom.net.Callback.IFailure;
import com.top.wisdom.net.Callback.IRequest;
import com.top.wisdom.net.Callback.ISuccess;
import com.top.wisdom.net.Loader.LoaderStyle;
import com.top.wisdom.net.RestClient.RestClient;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

public class RestClientBuilder {

    public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    //上下文
    private Context mContext;
    //请求的网址
    private String mURL = null;
    private Map<String, Object> mParams = PARAMS;
    //请求过程回调
    private ISuccess iSuccess = null;
    private IFailure iFailure = null;
    private IError iError = null;
    private IRequest iRequest = null;

    private ResponseBody mResponseBody = null;

    //文件名
    private File mFile = null;
    //下载路径
    private String mDownloadDir = null;
    //文件名
    private String mName = null;
    //扩展名（后缀）
    private String mExtension;

    //加载样式
    private LoaderStyle mLoaderStyle;


    //设置上下文
    public final RestClientBuilder withActivity(Context mContext) {
        this.mContext = mContext;
        return this;
    }


    //设置请求的url
    public final RestClientBuilder url(String url) {
        this.mURL = url;
        return this;
    }
    //设置请求参数
    public final RestClientBuilder param(Map<String, Object> params) {
        this.mParams = params;
        return this;
    }
    //设置请求参数
    public final RestClientBuilder param(String key, Object value) {
        if (mParams == null) {
            mParams = new WeakHashMap<>();
        }
        this.mParams.put(key, value);
        return this;
    }
    //请求返回成功信息
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.iSuccess = iSuccess;
        return this;
    }
    //请求返回失败信息
    public final RestClientBuilder failure(IFailure iFailure) {
        this.iFailure = iFailure;
        return this;
    }
    //请求返回错误信息
    public final RestClientBuilder error(IError iError) {
        this.iError = iError;
        return this;
    }
    //request请求
    public final RestClientBuilder request(IRequest iRequest) {
        this.iRequest = iRequest;
        return this;
    }
    //下载存储路径
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }
    //设置扩展名（后缀）
    public final RestClientBuilder extension(String mExtension) {
        this.mExtension = mExtension;
        return this;
    }
    //设置下载的文件名
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }
    //设置下载的文件名
    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mResponseBody = ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder loader(LoaderStyle mLoaderStyle) {
        this.mLoaderStyle = mLoaderStyle;
        return this;
    }

    public final RestClientBuilder loader() {
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }


    //生成管理器

    public RestClient build() {
        return new RestClient(mContext,
                mURL,
                mParams,
                iRequest,
                iSuccess,
                iFailure,
                iError,
                mResponseBody,
                mFile,
                mDownloadDir,
                mName,
                mExtension,
                mLoaderStyle
        );
    }
}

