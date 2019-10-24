package com.top.wisdom.net.RestClient;

import android.content.Context;

import com.top.wisdom.net.Builder.RestClientBuilder;
import com.top.wisdom.net.Callback.IError;
import com.top.wisdom.net.Callback.IFailure;
import com.top.wisdom.net.Callback.IRequest;
import com.top.wisdom.net.Callback.ISuccess;
import com.top.wisdom.net.Dowload.DownLoadHandler;
import com.top.wisdom.net.Loader.Loader;
import com.top.wisdom.net.Loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestClient {

    //上下文
    private Context mContext;
    //请求的网址
    private String mURL = null;
    private Map<String, Object> mParams;
    //请求过程回调
    private ISuccess iSuccess = null;
    private IFailure iFailure = null;
    private IError iError = null;
    private IRequest iRequest = null;

    private ResponseBody mResponseBody = null;

    //下载的文件名
    private File mFile = null;
    //下载路径
    private String mDownloadDir = null;
    //扩展名
    private String mExtension;
    //文件名
    private String mName = null;

    private LoaderStyle mLoaderStyle;


    public RestClient(Context mContext,
                      String mURL,
                      Map<String, Object> mParams,
                      IRequest iRequest,
                      ISuccess iSuccess,
                      IFailure iFailure,
                      IError iError,
                      ResponseBody mResponseBody,
                      File mFile,
                      String mDownloadDir,
                      String mName,
                      String mExtension,
                      LoaderStyle mLoaderStyle) {

        this.mContext = mContext;
        this.mURL = mURL;
        this.mParams = mParams;
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
        this.mResponseBody = mResponseBody;
        this.mFile = mFile;
        this.mDownloadDir = mDownloadDir;
        this.mExtension = mExtension;
        this.mName = mName;

        this.mLoaderStyle = mLoaderStyle;
    }


    public static RestClientBuilder Builder() {
        return new RestClientBuilder();
    }

    /**
     * request请求
     *
     * @param method
     */
    private void request(HttpMethod method) {

        final RestService service = RestCreater.getRestService();
        Call<String> call = null;
        if (iRequest != null) {
            iRequest.onRequestStart();
        }
        if (mLoaderStyle != null) {
            Loader.showLoading(mContext, mLoaderStyle);
        }
        switch (method) {
            case GET:
                call = service.get(mURL, mParams);
                break;
            case POST:
                call = service.post(mURL, mParams);
                break;
            case POST_RAW:
                call = service.postRaw(mURL, mResponseBody);
                break;
            case DELETE:
                call = service.delete(mURL, mParams);
                break;
            case PUT:
                call = service.put(mURL, mParams);
                break;

            case PUT_RAW:
                call = service.putRaw(mURL, mResponseBody);
                break;

            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), mFile);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", mFile.getName());

                call = RestCreater.getRestService().upLoad(mURL, body);
                break;
            case DOWNLOAD:

                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        if (call.isExecuted()) {
                            if (iSuccess != null) {
                                iSuccess.onSuccess(response.body());
                            }
                        }
                    } else {
                        if (iError != null) {
                            iError.onError(response.code(), response.message());
                        }
                    }
                    if (mLoaderStyle != null) {
                        Loader.dismissLoading();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (iFailure != null) {
                        iFailure.onFailure();
                    }
                    if (iRequest != null) {
                        iRequest.onRequestEnd();
                    }
                    if (mLoaderStyle != null) {
                        Loader.dismissLoading();
                    }
                }
            });
        }
    }

    /**
     * get请求
     */
    public final void get() {
        request(HttpMethod.GET);
    }

    /**
     * post请求
     */
    public final void post() {
        if (mResponseBody == null) {
            request(HttpMethod.POST);

        } else {
            if (!mParams.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    /**
     * put请求
     */
    public final void put() {
        if (mResponseBody == null) {
            request(HttpMethod.PUT);
        } else {
            if (!mParams.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * delete请求
     */
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    /**
     * download请求
     */
    public final void download() {
        new DownLoadHandler(mContext,
                mURL,
                mParams,
                iRequest,
                iSuccess,
                iFailure,
                iError,
                mName,
                mExtension,
                mDownloadDir
        ).handleDownLoad();
    }

}
