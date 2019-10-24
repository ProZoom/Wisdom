package com.top.wisdom.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.top.wisdom.R;
import com.top.wisdom.utils.ColorUtil;
import com.top.wisdom.utils.DrawableUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by Vector
 * on 2017/7/19 0019.
 */

public class UpdateDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "UpdateDialogFragment";

    public static final String TIPS = "请授权访问存储空间权限，否则App无法更新";

    public static boolean isShow = false;
    private TextView mContentTextView;
    private Button mUpdateOkButton;
    private NumberProgressBar mNumberProgressBar;
    private ImageView mIvClose;
    private TextView mTitleTextView;

    private LinearLayout mLlClose;
    //默认色
    private int mDefaultColor = 0xffe94339;
    private int mDefaultPicResId = R.mipmap.lib_update_app_top_bg;
    private ImageView mTopIv;
    private TextView mIgnore;
    private static Context mContext;

    private static AppUpdateBean mAppUpdateBean;


    //private interface
    /////////////////////////////////////////////////////


    public static UpdateDialogFragment newInstance(Context context,AppUpdateBean appUpdateBean) {
        UpdateDialogFragment fragment = new UpdateDialogFragment();
        mAppUpdateBean = appUpdateBean;
        mContext = context;

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isShow = true;
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.UpdateAppDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_update, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvents();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        isShow = false;
        super.onDestroyView();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (Integer.parseInt(mAppUpdateBean.newVersionCode) > getPackageCode(mContext)){
            super.show(manager, tag);
        }else {
            Toast.makeText(mContext, "没有新版本！", Toast.LENGTH_SHORT).show();
            //Snackbar.make(getActivity().findViewById(android.R.id.content),"没有新版本！",Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                //mUpdateOkButton

                break;
            case R.id.iv_close:
                //mIvClose
                this.dismiss();
                break;
            case R.id.tv_ignore:
                //mIgnore

                break;
        }
    }

    private void initData() {
        //设置主题色
        initTheme();
    }

    private void initView(View view) {
        //提示内容
        mContentTextView = view.findViewById(R.id.tv_update_info);
        //标题
        mTitleTextView = view.findViewById(R.id.tv_title);
        //更新按钮
        mUpdateOkButton = view.findViewById(R.id.btn_ok);
        //进度条
        mNumberProgressBar = view.findViewById(R.id.npb);
        //关闭按钮
        mIvClose = view.findViewById(R.id.iv_close);
        //关闭按钮+线 的整个布局
        mLlClose = view.findViewById(R.id.ll_close);
        //顶部图片
        mTopIv = view.findViewById(R.id.iv_top);
        //忽略
        mIgnore = view.findViewById(R.id.tv_ignore);

        if (mAppUpdateBean.forceUpdate) {
            // mIvClose.setVisibility(View.GONE);
            mLlClose.setVisibility(View.GONE);
        }
        mContentTextView.setText(mAppUpdateBean.updateDescription.replace("\\n", "\n"));
        mTitleTextView.setText("是否升级到" + mAppUpdateBean.newVersionName + "版本？");
    }

    private void initEvents() {
        mUpdateOkButton.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
        mIgnore.setOnClickListener(this);
    }

    /**
     * 初始化主题色
     */
    private void initTheme() {
        setDialogTheme(mDefaultColor, mDefaultPicResId);
    }

    /**
     * 设置
     *
     * @param color    主色
     * @param topResId 图片
     */
    private void setDialogTheme(int color, int topResId) {
        mTopIv.setImageResource(topResId);
        mUpdateOkButton.setBackgroundDrawable(DrawableUtil.getDrawable(dip2px(4, getActivity()), color));
        mNumberProgressBar.setProgressTextColor(color);
        mNumberProgressBar.setReachedBarColor(color);
        //随背景颜色变化
        mUpdateOkButton.setTextColor(ColorUtil.isTextColorDark(color) ? Color.BLACK : Color.WHITE);
    }

    private   int dip2px(int dip, Context context) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static class AppUpdateBean {

        private String id;
        private String packageName;

        private String newVersionCode;
        private String minVersionCode;
        private String newVersionName;


        private String apkUrl;
        private String apkSize;
        private String updateDescription;
        private boolean isUpdate;
        private boolean forceUpdate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getNewVersionCode() {
            return newVersionCode;
        }

        public void setNewVersionCode(String newVersionCode) {
            this.newVersionCode = newVersionCode;
        }

        public String getMinVersionCode() {
            return minVersionCode;
        }

        public void setMinVersionCode(String minVersionCode) {
            this.minVersionCode = minVersionCode;
        }

        public String getNewVersionName() {
            return newVersionName;
        }

        public void setNewVersionName(String newVersionName) {
            this.newVersionName = newVersionName;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getApkSize() {
            return apkSize;
        }

        public void setApkSize(String apkSize) {
            this.apkSize = apkSize;
        }

        public String getUpdateDescription() {
            return updateDescription;
        }

        public void setUpdateDescription(String updateDescription) {
            this.updateDescription = updateDescription;
        }

        public boolean isUpdate() {
            return isUpdate;
        }

        public void setUpdate(boolean update) {
            isUpdate = update;
        }

        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
        }
    }

    private  int getPackageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
}

