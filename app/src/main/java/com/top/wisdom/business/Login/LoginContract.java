package com.top.wisdom.business.Login;

import android.content.Context;

import com.top.wisdom.base.BaseView;
import com.top.wisdom.bean.BaseObjectBean;
import com.top.wisdom.bean.LoginBean;

import io.reactivex.Flowable;

public interface LoginContract {

    /**
     * 负责数据的请求，解析，过滤等数据操作
     */
    interface Model{
        Flowable<BaseObjectBean<LoginBean>> login(Context context,String username, String password);
    }

    /**
     * UI操作接口
     * 负责图示部分展示，图示事件处理，Activity，Fragment，Dialog，ViewGroup等呈现视图的组件都可以承担该角色
     */
    interface View extends BaseView {


    }

    /**
     * View和Model交互的桥梁。
     */
    interface Presenter {
        /**
         * 登陆
         *
         * @param context
         * @param username
         * @param password
         */
        void login(Context context,String username, String password);
    }
}
