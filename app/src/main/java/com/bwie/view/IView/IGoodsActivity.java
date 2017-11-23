package com.bwie.view.IView;

import com.bwie.adapter.GoodsAdapter;
import com.bwie.bean.GoodsBean;

import java.util.List;

/**
 * Created by ASUS on 2017/11/22.
 */

public interface IGoodsActivity {
    public void showList(List<GoodsBean.DataBean>groupList,List<List<GoodsBean.DataBean.DatasBean>>childList);
}
