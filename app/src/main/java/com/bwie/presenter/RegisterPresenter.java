package com.bwie.presenter;

import android.text.TextUtils;

import com.bwie.bean.BaseBean;
import com.bwie.model.IModel.IRegisterModel;
import com.bwie.model.RegisterModel;
import com.bwie.net.OnNetListener;
import com.bwie.view.IView.IRegisterActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ASUS on 2017/11/8.
 */

public class RegisterPresenter {
    private IRegisterActivity iRegisterActivity;
    private final IRegisterModel iRegisterModel;

    public RegisterPresenter(IRegisterActivity iRegisterActivity) {
        this.iRegisterActivity = iRegisterActivity;
        iRegisterModel=new RegisterModel();
    }


    private boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            //给用户提示，输入的账号不能为空
            iRegisterActivity.show("请输入密码");
            return false;
        }

        if (pwd.length() != 6) {
            iRegisterActivity.show("请输入6位密码");
            return false;
        }
        return true;
    }


    /**
     * 验证手机号是否正确
     *
     * @param account
     */
    private boolean checkAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            //给用户提示，输入的账号不能为空
            iRegisterActivity.show("请输入账号");
            return false;
        }
        if (!isMobileNO(account)) {
            iRegisterActivity.show("请输入正确的手机号");
            return false;
        }
        return true;
    }

    /*
   判断是否是手机号
    */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public void register() {
        String account = iRegisterActivity.getAccount();
        String pwd = iRegisterActivity.getPwd();
        //判断账号密码是否正确
        if (checkAccount(account) && checkPwd(pwd)) {
            iRegisterModel.register(account, pwd, new OnNetListener<BaseBean>() {
                @Override
                public void onSuccess(BaseBean baseBean) {
                    //成功以后，回到登陆界面
                    if (baseBean.getCode().equals("1")) {
                        iRegisterActivity.show(baseBean.getMsg());
                    } else {
                        iRegisterActivity.show(baseBean.getMsg());
                        iRegisterActivity.finishAc();
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }

    }

}
