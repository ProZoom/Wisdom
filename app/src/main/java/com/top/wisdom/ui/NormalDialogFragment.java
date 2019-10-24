package com.top.wisdom.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.top.wisdom.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public class NormalDialogFragment extends DialogFragment implements View.OnClickListener {

    private AppCompatTextView title;
    private AppCompatTextView tvContent;
    private AppCompatTextView btnSure;
    private AppCompatTextView btnCancel;

    private Context mContext;

    @SuppressLint("ValidFragment")
    public NormalDialogFragment(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.UpdateAppDialog);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_normal, container);

        initView(rootView);

        return rootView;
    }

    private void initView(View rootview) {
        title = rootview.findViewById(R.id.tv_title);
        tvContent = rootview.findViewById(R.id.tv_content);
        btnSure = rootview.findViewById(R.id.btn_sure);
        btnCancel = rootview.findViewById(R.id.btn_cancel);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }


    private int dip2px(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:

                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }


    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setMessage(String message){
        this.tvContent.setText(message);
    }
}
