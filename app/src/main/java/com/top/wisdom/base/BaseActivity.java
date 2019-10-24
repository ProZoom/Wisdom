package com.top.wisdom.base;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG ="BaseActivity";


    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(this.getLayoutId());

        Log.i(TAG,"-----------------onCreate------------------");
        unbinder = ButterKnife.bind(this);
        initView();
    }


    /**
     * 设置布局
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化视图
     */
    public abstract void initView();



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"-----------------onStart------------------");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"-----------------onResume------------------");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"-----------------onPause------------------");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"-----------------onStop------------------");

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();

        super.onDestroy();
        Log.i(TAG,"-----------------onDestroy------------------");

    }
}
