package com.top.wisdom.business.syncData;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.top.wisdom.R;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SyncDataActivity2 extends AppCompatActivity {

    private static final String TAG ="SyncDataActivity";


    @BindView(R.id.toolbar_sync)
    Toolbar toolbarSync;


    private BluetoothReceiver receiver;
    private BluetoothAdapter mBluetoothAdapter;
    private List<String> devices;
    private List<BluetoothDevice> deviceList;
   // private Bluetooth client;

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

        toolbarSync.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            //弹出对话框提示用户是后打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);

        registerReceiver(receiver,filter);



        scanBlue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    /**
     * 设备是否支持蓝牙  true为支持
     * @return
     */
    public boolean isSupportBlue(){
        return mBluetoothAdapter != null;
    }

    /**
     * 扫描的方法 返回true 扫描成功
     * 通过接收广播获取扫描到的设备
     * @return
     */
    public boolean scanBlue(){
        if (!isBlueEnable()){
            Log.e(TAG, "Bluetooth not enable!");
            return false;
        }
        //当前是否在扫描，如果是就取消当前的扫描，重新扫描
        if (mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();
        }
        //此方法是个异步操作，一般搜索12秒
        return mBluetoothAdapter.startDiscovery();
    }

    /**
     * 蓝牙是否打开   true为打开
     * @return
     */
    public boolean isBlueEnable(){
        return isSupportBlue() && mBluetoothAdapter.isEnabled();
    }

    /**
     * 自动打开蓝牙（异步：蓝牙不会立刻就处于开启状态）
     * 这个方法打开蓝牙不会弹出提示
     */
    public void openBlueAsyn(){
        if (isSupportBlue()) {
            mBluetoothAdapter.enable();
        }
    }

    private void startDiscovery() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);//开启搜索
        registerReceiver(receiver, filter);
        //filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//搜索完成
        //registerReceiver(receiver, filter);
        mBluetoothAdapter.startDiscovery();
    }

    /**
     *扫描广播接收类
     * Created by zqf on 2018/7/6.
     */

    public class BluetoothReceiver extends BroadcastReceiver {
        //private  final String TAG = ScanBlueReceiver.class.getName();
        //private ScanBlueCallBack callBack;

    /*    public ScanBlueReceiver(ScanBlueCallBack callBack){
            this.callBack = callBack;
        }*/

        //广播接收器，当远程蓝牙设备被发现时，回调函数onReceiver()会被执行
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "action:" + action);
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            switch (action){
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                    Log.d(TAG, "开始扫描...");
                    //callBack.onScanStarted();

                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    Log.d(TAG, "结束扫描...");
                    //callBack.onScanFinished();
                    break;
                case BluetoothDevice.ACTION_FOUND:
                    Log.d(TAG, "发现设备..."+ device.getName());
                    //callBack.onScanning(device);

                    break;
            }
        }
    }
}
