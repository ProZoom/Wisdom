package com.top.wisdom.business.main;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.widget.Toolbar;

import com.top.wisdom.base.BaseView;

public interface MainContract {

    /**
     * 负责数据的请求，解析，过滤等数据操作
     */
    interface Model{
        void updateMainAdapterData(Context context,MainAdapter adapter);
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

        void updateMainAdapterData(Activity activity, MainAdapter adapter);
    }
}
