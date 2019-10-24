package com.top.wisdom.base;


/**
 *BasePresenter接口中有start方法，
 * 用于Presenter开始获取数据并调用View进行界面显示，
 * 在View的实现类Fragment中的onResume方法中调用。
 */
public class BasePresenter<V extends BaseView>{
    protected V mView;


    /**
     * 绑定view，一般在初始化中调用该方法
     * @param view view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mView = null;
    }

    /**
     * View是否绑定
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

}
