package com.top.wisdom.net.RestClient;

import com.top.wisdom.net.base.ConfigType;
import com.top.wisdom.net.base.Top;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：ProZoom
 * 时间：2018/10/19
 * 描述：创建器
 */
public class RestCreater {

    /**
     * 参数缓冲区
     */
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
        public static final ArrayList<Interceptor> mInterceptors = new ArrayList<>();
    }

    /**
     * params get
     * @return
     */
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * ArrayList<Interceptor> get
     * @return
     */
    public static ArrayList<Interceptor> getInterceptors() {
        return ParamsHolder.mInterceptors;
    }


    /**
     * 获取服务接口
     * @return
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


    /**
     *Retrofit缓冲区
     */
    private static final class RetrofitHolder {
        //private static final String BASE_URL = (String) Top.getConfigurations(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(Top.getConfigurations(ConfigType.API_HOST).toString())
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    /**
     *OkHttpHolder缓冲区
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final ArrayList<Interceptor> INTERCEPTORS = ParamsHolder.mInterceptors;

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     *
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }
}
