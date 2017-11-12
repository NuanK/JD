package com.bwie.model.IModel;

import com.bwie.bean.BaseBean;
import com.bwie.net.OnNetListener;

/**
 * Created by ASUS on 2017/11/8.
 */

public interface IRegisterModel {
    public void register(String account, String pwd, OnNetListener<BaseBean> onNetListener);
}
