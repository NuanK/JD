package com.bwie.model.IModel;

import com.bwie.bean.GoodsBean;
import com.bwie.net.OnNetListener;

/**
 * Created by ASUS on 2017/11/22.
 */

public interface IGoodsModel {
    public void getGoods(OnNetListener<GoodsBean> onNetListener);
}
