package com.bwie.presenter;

import com.bwie.bean.GoodsBean;
import com.bwie.model.GoodsModel;
import com.bwie.model.IModel.IGoodsModel;
import com.bwie.net.OnNetListener;
import com.bwie.view.IView.IGoodsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/11/22.
 */

public class GoodsPresenter {

    private IGoodsModel iGoodsModel;
    private IGoodsActivity iGoodsActivity;

    public GoodsPresenter(IGoodsActivity iGoodsActivity){
        this.iGoodsActivity=iGoodsActivity;
        iGoodsModel=new GoodsModel();
    }

    public void getGoods(){
       iGoodsModel.getGoods(new OnNetListener<GoodsBean>() {
           @Override
           public void onSuccess(GoodsBean goodsBean) {
               List<GoodsBean.DataBean> data = goodsBean.getData();
               List<List<GoodsBean.DataBean.DatasBean>> childList = new ArrayList<List<GoodsBean.DataBean.DatasBean>>();
               for (int i = 0; i <data.size() ; i++) {
                   List<GoodsBean.DataBean.DatasBean> datas = data.get(i).getDatas();
                   childList.add(datas);
               }
               iGoodsActivity.showList(data,childList);
           }

           @Override
           public void onFailure(Exception e) {

           }
       });
    }

}
