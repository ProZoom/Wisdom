package com.top.wisdom.business.Login;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.top.wisdom.R;
import com.top.wisdom.base.BaseMvpActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;


public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {


    @BindView(R.id.iv_sign_in_icon)
    AppCompatImageView ivSignInIcon;
    @BindView(R.id.et_sign_in_user)
    TextInputEditText etSignInUser;
    @BindView(R.id.et_sign_in_password)
    TextInputEditText etSignInPassword;
    @BindView(R.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R.id.tv_sign_in_link)
    AppCompatTextView tvSignInLink;
    @BindView(R.id.tv_no_sign_in)
    AppCompatTextView tvNoSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        btnSignIn.setOnClickListener(this);
        tvNoSignIn.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
    }

    /**
     * @return 帐号
     */
    private String getUsername() {
        return etSignInUser.getText().toString().trim();
    }

    /**
     * @return 密码
     */
    private String getPassword() {
        return etSignInPassword.getText().toString().trim();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_no_sign_in:
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.btn_sign_in:
                if (getUsername().isEmpty() || getPassword().isEmpty()) {
                    Toast.makeText(this, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.login(LoginActivity.this,etSignInUser.getText().toString(),etSignInPassword.getText().toString());
                break;
        }


    }
}
