package com.top.wisdom.base;

import android.app.Activity;
import android.content.Context;

import com.top.wisdom.business.main.MainActivity;

/**
 * @author azheng
 * @date 2018/4/24.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public abstract class BaseMvpFragment<T extends BasePresenter>  extends BaseFragment implements BaseView{

    protected T mPresenter;

    protected MainActivity parentActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity= (MainActivity) context;
    }


    @Override
    public void onDestroyView() {
        //super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }
}
