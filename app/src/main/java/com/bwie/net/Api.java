package com.bwie.net;

/**
 * Created by ASUS on 2017/11/8.
 */

public interface Api {
    public static boolean isOnline = false;
    public static final String DEV = "http://120.27.23.105/";
    public static final String WROK = "";
    public static final String HOST = isOnline ? WROK : DEV;

    public static final String LOGIN = HOST + "user/login";//登陆
    public static final String REGISTER = HOST + "user/reg";//注册
    public static final String CLASS = HOST + "product/getCatagory";//分类
    public static final String PRODUCT_CATAGORY = HOST + "product/getProductCatagory";//商品子分类接口
    public static final String GWCurl="http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=evaluation";//购物车

}
