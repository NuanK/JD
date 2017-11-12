package com.bwie.view.IView;

import com.bwie.bean.Catagory;
import com.bwie.bean.ProductCatagoryBean;

import java.util.List;

/**
 * Created by ASUS on 2017/11/9.
 */

public interface IClassActivity {
    public void showData(List<Catagory.DataBean>list);
    public void showElvData(List<ProductCatagoryBean.DataBean> list);
}
