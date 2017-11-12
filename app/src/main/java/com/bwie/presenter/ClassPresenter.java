package com.bwie.presenter;

import com.bwie.bean.Catagory;
import com.bwie.bean.ProductCatagoryBean;
import com.bwie.model.ClassModel;
import com.bwie.model.IModel.IClassModel;
import com.bwie.net.OnNetListener;
import com.bwie.view.IView.IClassActivity;

import java.util.List;

/**
 * Created by ASUS on 2017/11/9.
 */

public class ClassPresenter {
    private IClassModel iClassModel;
    private IClassActivity iClassActivity;

    public ClassPresenter(IClassActivity iClassActivity) {
        this.iClassActivity = iClassActivity;
        iClassModel = new ClassModel();
    }
    public void getCatagory(){
        iClassModel.getCatagory(new OnNetListener<Catagory>() {
            @Override
            public void onSuccess(Catagory catagory) {
                iClassActivity.showData(catagory.getData());
                //拿到右侧第一条数据
                List<Catagory.DataBean>dataBeen=catagory.getData();
                int cid = dataBeen.get(0).getCid();
                //获取右侧的数据
                getProductCatagory(cid + "");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getProductCatagory(String cid){
        iClassModel.getProductCatagory(cid + "", new OnNetListener<ProductCatagoryBean>() {
            @Override
            public void onSuccess(ProductCatagoryBean productCatagoryBean) {
                //给二级列表设置数据
                iClassActivity.showElvData(productCatagoryBean.getData());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
