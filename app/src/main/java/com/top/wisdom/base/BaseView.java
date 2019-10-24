package com.top.wisdom.base;

import com.uber.autodispose.AutoDisposeConverter;

/**
 * BaseView接口中有setPresenter方法，
 * 用于将Presenter实例传入View中，
 * 在Presenter的实现类的构造方法中调用。
 * @param <T>
 */
public interface BaseView {

    /**
     * 显示加载中
     */
    //void showLoading();

    /**
     * 隐藏加载
     */
    //void hideLoading();

    /**
     * 数据获取失败
     * @param throwable
     */
    //void onError(Throwable throwable);

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();

}
