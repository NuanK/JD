package com.bwie.model;

import com.bwie.bean.LoginBean;
import com.bwie.model.IModel.ILoginModel;
import com.bwie.net.Api;
import com.bwie.net.HttpUtils;
import com.bwie.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2017/11/8.
 */

public class LoginModel extends BaseModel implements ILoginModel {
    @Override
    public void login(String account, String pwd, final OnNetListener<LoginBean> onNetListener) {
        Map<String,String> params=new HashMap<>();
        params.put("mobile",account);
        params.put("password",pwd);
        HttpUtils.getHttpUtils().doPost(Api.LOGIN, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNetListener.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string=response.body().string();
                final LoginBean loginBean=new Gson().fromJson(string,(Type)LoginBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(loginBean);
                    }
                });


            }
        });

    }
}
