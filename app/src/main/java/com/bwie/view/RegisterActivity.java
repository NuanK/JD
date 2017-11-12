package com.bwie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.presenter.RegisterPresenter;
import com.bwie.test.a09aproject.R;
import com.bwie.view.IView.IRegisterActivity;

public class RegisterActivity extends AppCompatActivity implements IRegisterActivity, View.OnClickListener {

    private ImageView mRegisterBack;
    /**
     * 手机号
     */
    private EditText mEtPhone;
    /**
     * 请输入密码
     */
    private EditText mEtPwd;
    private ImageView mImgPwd;
    /**
     * 注册
     */
    private Button mBtnRegister;
    private RegisterPresenter registerPresenter;
    private LinearLayout mActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter = new RegisterPresenter(this);
        initView();
    }

    private void initView() {
        mRegisterBack = (ImageView) findViewById(R.id.register_back);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mImgPwd = (ImageView) findViewById(R.id.img_pwd);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
        mRegisterBack.setOnClickListener(this);
        mActivityLogin = (LinearLayout) findViewById(R.id.activity_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_register:
                //注册
                registerPresenter.register();
                break;
            case R.id.register_back:
                this.finish();
                break;
        }
    }

    @Override
    public String getAccount() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtPwd.getText().toString().trim();
    }

    @Override
    public void finishAc() {
        finish();
    }

    @Override
    public void show(String str) {
        Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
    }


}
