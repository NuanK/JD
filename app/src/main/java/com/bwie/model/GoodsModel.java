package com.bwie.model;

import com.bwie.bean.GoodsBean;
import com.bwie.model.IModel.IGoodsModel;
import com.bwie.net.Api;
import com.bwie.net.HttpUtils;
import com.bwie.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2017/11/22.
 */

public class GoodsModel extends BaseModel implements IGoodsModel {

    @Override
    public void getGoods(final OnNetListener<GoodsBean> onNetListener) {
        HttpUtils.getHttpUtils().doGet(Api.GWCurl, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final GoodsBean goodsBean = new Gson().fromJson(string, GoodsBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(goodsBean);
                    }
                });
            }
        });
    }
}
