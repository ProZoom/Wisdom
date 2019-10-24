package com.top.updateapp.listener;


import com.top.updateapp.UpdateAppBean;



public interface IUpdateDialogFragmentListener {
    /**
     * 当默认的更新提示框被用户点击取消的时候调用
     * @param updateApp updateApp
     */
    void onUpdateNotifyDialogCancel(UpdateAppBean updateApp);
}
