package com.bwie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.presenter.LoginPresenter;
import com.bwie.test.a09aproject.R;
import com.bwie.view.IView.ILoginActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener ,ILoginActivity{

    private ImageView mLoginBack;
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
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 请注册
     */
    private TextView mTvRegister;
    private ImageView mImgWeixin;
    private ImageView mImgMqq;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //关联presenter
        loginPresenter=new LoginPresenter(this);
        initView();
    }

    private void initView() {
        mLoginBack = (ImageView) findViewById(R.id.login_back);
        mLoginBack.setOnClickListener(this);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mImgPwd = (ImageView) findViewById(R.id.img_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
        mImgWeixin = (ImageView) findViewById(R.id.img_weixin);
        mImgMqq = (ImageView) findViewById(R.id.img_mqq);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_back:
                this.finish();
                break;
            case R.id.btn_login:
                loginPresenter.login();
                break;
            case R.id.tv_register:
                //注册
                loginPresenter.register();
                break;
        }
    }

    @Override
    public String getAccount() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public String getpwd() {
        return mEtPwd.getText().toString().trim();
    }

    @Override
    public void show(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, str,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void toRegisterAc() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void toClassAc() {

    }
}
