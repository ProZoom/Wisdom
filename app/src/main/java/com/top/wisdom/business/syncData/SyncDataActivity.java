package com.top.wisdom.business.syncData;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.top.wisdom.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class SyncDataActivity extends AppCompatActivity implements BluetoothAdapter.LeScanCallback {

    private static final String TAG = "SyncDataActivity";
    private static final int REQUEST_PERMISSION_ACCESS_LOCATION = 23;


    @BindView(R.id.toolbar_sync)
    Toolbar toolbarSync;
    @BindView(R.id.ll_sync)
    RelativeLayout llSync;
    @BindView(R.id.rv_syncdata)
    RecyclerView rvSyncdata;


    private BluetoothAdapter mBluetoothAdapter;
    private List<String> devices;
    private List<BluetoothDevice> deviceList = new ArrayList<>();
    // private Bluetooth client;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syncdata);
        ButterKnife.bind(this);
        toolbarSync.setTitle(getResources().getString(R.string.sync_title));
        toolbarSync.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarSync);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        toolbarSync.setNavigationOnClickListener(v -> onBackPressed());

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            //没有有蓝牙功能模块
            Snackbar.make(llSync, "该设备没有蓝牙模块!", Snackbar.LENGTH_LONG).show();
        }

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            //弹出对话框提示用户是后打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);
        }
        //scanBlue();
        requestPermission();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        rvSyncdata.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        //layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        //rvSyncdata.setAdapter(recycleAdapter);
        //设置分隔线
        //rvSyncdata.addItemDecoration(new DividerGridItemDecoration(this));
        //设置增加或删除条目的动画
        rvSyncdata.setItemAnimator(new DefaultItemAnimator());

        //rvSyncdata.setAdapter();

    }


    /**
     * 设备是否支持蓝牙  true为支持
     *
     * @return
     */
    public boolean isSupportBlue() {
        return mBluetoothAdapter != null;
    }


    /**
     * 蓝牙是否打开   true为打开
     *
     * @return
     */
    public boolean isBlueEnable() {
        return isSupportBlue() && mBluetoothAdapter.isEnabled();
    }

    /**
     * 自动打开蓝牙（异步：蓝牙不会立刻就处于开启状态）
     * 这个方法打开蓝牙不会弹出提示
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void scanLeDevice(final boolean enable) {
        if (enable) {//true
            //15秒后停止搜索
            new Handler().postDelayed(() -> {
                //isScanning = false;
                mBluetoothAdapter.stopLeScan(SyncDataActivity.this::onLeScan);
            }, 12000);
            //isScanning = true;
            //isScanning = false;
            mBluetoothAdapter.startLeScan(this); //开始搜索

        } else {//false
            //isScanning = false;
            mBluetoothAdapter.stopLeScan(this);//停止搜索
        }
    }


    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

        if (device != null) {
            Log.d(TAG, "发现设备..." + device.getName());
            deviceList.add(device);
            device.toString();

        }
    }

    private List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkAccessFinePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkAccessFinePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION_ACCESS_LOCATION);
                Log.d(TAG, "没有权限，请求权限");
                return;
            }
            Log.d(TAG, "已有定位权限");
        }
        //做下面该做的事
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_ACCESS_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "开启权限permission granted!");
                    //做下面该做的事
                    scanLeDevice(true);
                } else {
                    Log.d(TAG, "没有定位权限，请先开启!");
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void scan(View view) {
        scanLeDevice(true);

    }
}
