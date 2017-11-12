package com.bwie.model.IModel;

import com.bwie.bean.LoginBean;
import com.bwie.net.OnNetListener;

/**
 * Created by ASUS on 2017/11/8.
 */

public interface ILoginModel {
    public void login(String account, String pwd, OnNetListener<LoginBean>onNetListener);
}
