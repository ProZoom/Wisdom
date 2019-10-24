package com.top.wisdom.business.splash;

import android.app.Activity;

import com.top.wisdom.base.BaseView;

import androidx.appcompat.widget.AppCompatTextView;

public interface SplashContract {

    /**
     * 负责数据的请求，解析，过滤等数据操作
     */
    interface Model{
        void toMainActivity(Activity activity,long delayMs);
        String getVersion(Activity activity);
    }

    /**
     * UI操作接口
     * 负责图示部分展示，图示事件处理，Activity，Fragment，Dialog，ViewGroup等呈现视图的组件都可以承担该角色
     */
    interface View extends BaseView {
        //void setVersion(Activity activity);

    }

    /**
     * View和Model交互的桥梁。
     */
    interface Presenter {
        void toMainActivity(Activity activity,long delayMs);

        void setVersion(Activity activity, AppCompatTextView tvVersion);
    }

}
