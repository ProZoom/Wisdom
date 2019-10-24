package com.top.wisdom.net.base;

import android.content.Context;

/**
 * 作者：ProZoom
 * 时间：2018/10/17
 * 描述：
 */
public final class Top {

    public static Configurator init(Context context){
        getConfigurator().getTopConfigs().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfigurations(Object key){
        return getConfigurator().getConfiguration(key);
    }

}
