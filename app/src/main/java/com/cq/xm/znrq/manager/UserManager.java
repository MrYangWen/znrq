package com.cq.xm.znrq.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cq.xm.znrq.XApplication;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.ui.activity.LoginAct;
import com.cq.xm.znrq.util.LogUtil;
import com.cq.xm.znrq.util.PreHelper;
import com.cq.xm.znrq.util.PreferenceKey;


/**
 * Created by JackMar on 2016/12/14.
 * 邮箱：1261404794@qq.com
 */

public class UserManager {
    static UserManager single = new UserManager();

    public synchronized static UserManager getInstance() {
        return single;
    }

    private UserManager() {
    }


    /**
     * 检查登录状态
     *
     * @param context
     * @param toLogin 跳转到登陆
     * @return
     */
    public boolean checkLogin(Context context, boolean toLogin) {
        boolean loginState = false;
        try {
            loginState = PreHelper.defaultCenter().getBooleanData(PreferenceKey.LOGIN_STATE);
            if (!loginState) {
                if (toLogin) {
                    context.startActivity(new Intent(context, LoginAct.class));
                }
            }
        } catch (Exception e) {
            LogUtil.e(e.toString());
        }
        return loginState;
    }

    /**
     * 更新缓存得用户信息
     *
     * @param user
     */
    public void updateUser(Activity context, UserEntity user, boolean needFinish) {
        if (user != null) {
            PreHelper.defaultCenter().putObject(PreferenceKey.USER_INFO, user);
            PreHelper.defaultCenter().setData(PreferenceKey.LOGIN_STATE, true);
            if (needFinish) {
                context.finish();
            }
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserEntity getUser() {
        UserEntity infoBean = null;
        if (UserManager.getInstance().checkLogin(XApplication.getInstance(), true)) {
            try {
                infoBean = PreHelper.defaultCenter().getObject(PreferenceKey.USER_INFO, UserEntity.class);
            } catch (Exception e) {

            }
        }
        return infoBean;
    }

    /**
     * 获取用户Id
     *
     * @return
     */
    public String getUserId() {
        if (PreHelper.defaultCenter().getObject(PreferenceKey.USER_INFO, UserEntity.class) != null) {
            return PreHelper.defaultCenter().getObject(PreferenceKey.USER_INFO, UserEntity.class).getAccount().getId() + "";
        }
        return "";
    }


    /**
     * 退出登陆
     */
    public void logOut(Context context) {
        PreHelper.defaultCenter().setData(PreferenceKey.LOGIN_STATE, false);
//        LoginAct.start(context);
    }
}
