package com.bwie.model.IModel;

import com.bwie.bean.GoodsBean;
import com.bwie.net.OnNetListener;

public interface IGoodsModel {
    public void getGoods(OnNetListener<GoodsBean> onNetListener);
}
