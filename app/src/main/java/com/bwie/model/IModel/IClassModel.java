package com.bwie.model.IModel;

import com.bwie.bean.Catagory;
import com.bwie.bean.ProductCatagoryBean;
import com.bwie.net.OnNetListener;

/**
 * Created by ASUS on 2017/11/9.
 */

public interface IClassModel {
    public void getCatagory(OnNetListener<Catagory> onNetListener);
    public void getProductCatagory(String cid, OnNetListener<ProductCatagoryBean> onNetListener);
}
