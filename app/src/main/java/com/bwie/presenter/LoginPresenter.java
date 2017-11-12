package com.bwie.presenter;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bwie.bean.LoginBean;
import com.bwie.model.IModel.ILoginModel;
import com.bwie.model.IModel.IRegisterModel;
import com.bwie.model.RegisterModel;
import com.bwie.model.LoginModel;
import com.bwie.net.OnNetListener;
import com.bwie.view.IView.ILoginActivity;

/**
 * Created by ASUS on 2017/11/8.
 */

public class LoginPresenter {
    private ILoginActivity iLoginActivity;
    private final ILoginModel iLoginModel;
    private final IRegisterModel iRegisterModel;

    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
        iLoginModel = new LoginModel();
        iRegisterModel=new RegisterModel();
    }
    public void login(){
        String account=iLoginActivity.getAccount();
        String pwd=iLoginActivity.getpwd();
        //判断账号密码是否正确
        if (checkAccount(account)&&checkPwd(pwd)){
            //去调用model，进行登陆
            iLoginModel.login(account, pwd, new OnNetListener<LoginBean>() {
                @Override
                public void onSuccess(LoginBean loginBean) {
                    //保存登陆成功后的数据，可以保存到SP,也可以保存到数据库
                    iLoginActivity.show(loginBean.getMsg());

                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }
    }

    /**
     * 验证手机号是否正确
     *
     * @param account
     */

    private boolean checkAccount(String account){
        if (TextUtils.isEmpty(account)){
            //给用户提示，输入的账号不能为空
            iLoginActivity.show("请输入账号");
            return false;
        }
        if (!isMobileNO(account)){
            iLoginActivity.show("请输入正确的手机号");
            return false;
        }

        return true;
    }

     /*
    判断是否是手机号
     */
    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证密码是否正确
     *
     * @param pwd
     */

    public boolean checkPwd(String pwd){
        if (TextUtils.isEmpty(pwd)){
            //给用户提示，输入的密码不能为空
            iLoginActivity.show("请输入密码");
            return false;
        }
        if (pwd.length()!=6){
            iLoginActivity.show("请输入6位密码");
            return false;
        }

        return true;
    }

    public void register() {
        //其实就是跳转到注册页面
        iLoginActivity.toRegisterAc();
    }

}
