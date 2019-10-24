package com.top.wisdom.net.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 作者：ProZoom
 * 时间：2018/10/17
 * 描述：
 */
public class Configurator {

    private static final HashMap<Object, Object> TOP_CONFIGS = new HashMap<>();

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();


    private Configurator() {
        TOP_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }


    public static Configurator getInstance() {
        return Holder.INSTANT;
    }


    final HashMap<Object, Object> getTopConfigs() {
        return TOP_CONFIGS;
    }


    public static class Holder {
        public static final Configurator INSTANT = new Configurator();
    }

    public final void configure() {
        TOP_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }



    ///////////////////////////////////////////////////////////////////////
    public final Configurator withAPIHost(String host) {
        TOP_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }


    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        TOP_CONFIGS.put(ConfigType.INTERCEPTORS,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        TOP_CONFIGS.put(ConfigType.INTERCEPTORS,INTERCEPTORS);
        return this;
    }



    public final Configurator withActivity(Activity activity) {
        TOP_CONFIGS.put(ConfigType.ACTIVITY,activity);
        return this;
    }

    public final Configurator withJavaScriptInterface(String name){
        TOP_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE,name);
        return this;
    }

/////////////////////////////////////////////////////////////////////////////

    private void checkConfiguration() {
        final boolean isReady = (boolean) TOP_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not read,call configure!");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        return (T) TOP_CONFIGS.get(key);
    }
}
