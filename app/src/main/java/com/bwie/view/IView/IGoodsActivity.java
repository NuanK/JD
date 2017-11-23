package com.bwie.view.IView;

import com.bwie.adapter.GoodsAdapter;
import com.bwie.bean.GoodsBean;

import java.util.List;


public interface IGoodsActivity {
    public void showList(List<GoodsBean.DataBean>groupList,List<List<GoodsBean.DataBean.DatasBean>>childList);
}
