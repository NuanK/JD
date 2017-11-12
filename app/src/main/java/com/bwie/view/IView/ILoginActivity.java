package com.bwie.view.IView;

/**
 * Created by ASUS on 2017/11/8.
 */

public interface ILoginActivity {
    public String getAccount();
    public String getpwd();

    public void show(String str);
    public void toRegisterAc();
    public void toClassAc();
}
