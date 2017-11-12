package com.bwie.net;

/**
 * Created by ASUS on 2017/11/8.
 */

public interface OnNetListener<T> {
    //成功回调
    public void onSuccess(T t);

    //失败回调
    public void onFailure(Exception e);
}
