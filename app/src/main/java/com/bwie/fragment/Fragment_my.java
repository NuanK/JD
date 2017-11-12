package com.bwie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwie.test.a09aproject.R;
import com.bwie.view.LoginActivity;

/**
 * Created by ASUS on 2017/11/9.
 */

public class Fragment_my extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView mMyHead;
    /**
     * 登录/注册>
     */
    private TextView mMyLogin;
    /**
     * 代付款
     */
    private RadioButton mFukuan;
    /**
     * 待收货
     */
    private RadioButton mShouhuo;
    /**
     * 待评价
     */
    private RadioButton mPingjia;
    /**
     * 退换/售后
     */
    private RadioButton mTuihuan;
    /**
     * 我的订单
     */
    private RadioButton mDingdan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mMyHead = (ImageView) view.findViewById(R.id.my_head);
        mMyLogin = (TextView) view.findViewById(R.id.my_login);
        mMyLogin.setOnClickListener(this);
        mFukuan = (RadioButton) view.findViewById(R.id.fukuan);
        mShouhuo = (RadioButton) view.findViewById(R.id.shouhuo);
        mPingjia = (RadioButton) view.findViewById(R.id.pingjia);
        mTuihuan = (RadioButton) view.findViewById(R.id.tuihuan);
        mDingdan = (RadioButton) view.findViewById(R.id.dingdan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.my_login:
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
