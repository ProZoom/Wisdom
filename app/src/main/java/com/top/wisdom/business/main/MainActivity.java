package com.top.wisdom.business.main;

import android.content.Intent;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.top.wisdom.R;
import com.top.wisdom.base.BaseMvpActivity;
import com.top.wisdom.business.Login.LoginActivity;
import com.top.wisdom.business.syncData.SyncDataActivity;
import com.top.wisdom.net.RestClient.RestClient;
import com.top.wisdom.ui.NormalDialogFragment;
import com.top.wisdom.ui.UpdateDialogFragment;
import com.top.wisdom.utils.ReflexUtils;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity";


    @BindView(R.id.content_frame)
    FrameLayout contentFrame;


    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;

    public Toolbar getToolbar() {
        return toolbar;
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    ActionBarDrawerToggle toggle;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //初始化ToolBar和DrawerLayout
        initToolBarAndDrawerLayout();
        //为悬浮控件设置监听
        fab.setOnClickListener(this);
        MainFragment mainFragment = MainFragment.newInstance();
        fragmentManager = getSupportFragmentManager();
        toFragment(mainFragment);
        commitFragment();
    }



    public void initToolBarAndDrawerLayout() {

        //back(false);
        toolbar.setTitle(getResources().getString(R.string.company_name));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);



        //mDrawerLayout.setDrawerShadow(R.drawable.wisdom, GravityCompat.START);
        //ActionBarDrawerToggle  是 DrawerLayout.DrawerListener实现,改变android.R.id.home返回图标。
        //.Drawer拉出、隐藏，带有android.R.id.home动画效果。.监听Drawer拉出、隐藏；
        toggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // drawer.setDrawerListener(toggle);
        //上面的监听被下面的就监听给取代了，由于回报空指针异常，google推出了addDrawerListener()，
        // 对它做了空指针的判断
        mDrawerLayout.addDrawerListener(toggle);
        //该方法会自动和actionBar关联, 将开关的图片显示在了action上，如果不设置，也可以有抽屉的效果，不过是默认的图标
        toggle.syncState();
        ReflexUtils.setDrawerLeftEdgeSize(this, mDrawerLayout, 0.4f);
        //为侧边栏的每一个子item设置监听
        navView.setNavigationItemSelectedListener(this);
        //为NavigationView设置头布局
        View headerView = navView.inflateHeaderView(R.layout.drawerlayout_header);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.drawerlayout_header_background_img);
        imageView.setOnClickListener(this);
        //mDrawerLayout.addView(headerView);
    }

    public void toggleDrawer(){
        toggle.syncState();
    }
    public void setBack(boolean isBack){
        // toolbar.setNavigationIcon(R.drawable.ic_launcher_background);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isBack);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(isBack); //设置返回键可用
    }


    public void closeDrawerLayout(){
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unLockDrawerLayout(){
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_sync:
                startActivity(new Intent(MainActivity.this, SyncDataActivity.class));
                //Toast.makeText(this, "click setting!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_update:
                //Toast.makeText(this, "click update!", Toast.LENGTH_SHORT).show();
                RestClient.Builder()
                        .withActivity(this)
                        .url("com/top/apk/wisdom/update")
                        .success(response -> {

                            Logger.json(response);

                            Gson gson = new Gson();

                            try {
                                JSONObject responseRaw = new JSONObject(response);
                                String data = responseRaw.getString("data");

                                UpdateDialogFragment.AppUpdateBean appUpdateBean = gson.fromJson(data, UpdateDialogFragment.AppUpdateBean.class);

                                UpdateDialogFragment updateDialogFragment = UpdateDialogFragment.newInstance(MainActivity.this,appUpdateBean);

                                updateDialogFragment.show((MainActivity.this).getSupportFragmentManager(), "UpdateAppDialog");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        })
                        .failure(() -> {
                           // Toast.makeText(this, "找不到ip地址！", Toast.LENGTH_SHORT).show();

                            Snackbar snackbar = Snackbar.make(mDrawerLayout, "找不到ip地址！", Snackbar.LENGTH_SHORT);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                            //文字的颜色
                            ((TextView) snackbarView.findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(android.R.color.black));
                            //按钮的颜色
                            snackbar.show();
                        })
                        .loader()
                        .build()
                        .get();

                break;
            case R.id.nav_appinfos:
                NormalDialogFragment dialogFragment=new NormalDialogFragment(MainActivity.this);
                dialogFragment.setTitle("应用说明");
                dialogFragment.setMessage("应用版权说明");
                dialogFragment.show((MainActivity.this).getSupportFragmentManager(), "NormalDialog");
                break;
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.drawerlayout_header_background_img:
                //Toast.makeText(this, "click drawer header!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

                break;
            case R.id.fab:
                Toast.makeText(this, "click FloatingActionButton!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //按物理返回键的时候，收缩侧边栏
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void setToolbarTitle(String title) {
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle(title);
    }

    public void toFragmentBackStack(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
    }

    public void toFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        // transaction.addToBackStack(null);
    }

    public void commitFragment() {
        transaction.commit();
    }

}
