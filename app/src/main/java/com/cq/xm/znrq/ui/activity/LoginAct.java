package com.cq.xm.znrq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.ui.TabAct;
import com.cq.xm.znrq.view.dialog.UrlSettingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：{大鹏} on 2017/8/14 10:47
 * 邮箱：919142784@qq.com
 * 页面名称：登錄
 */
public class LoginAct extends BaseActivity {

    @Bind(R.id.et_mobile)
    EditText mEtMobile;
    @Bind(R.id.et_psd)
    EditText mEtPsd;
    @Bind(R.id.iv_login)
    TextView mIvLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        
    }

    @OnClick({R.id.tv_url, R.id.iv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_url:
                UrlSettingDialog dialog = new UrlSettingDialog();
                dialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.iv_login:
                String phone = mEtMobile.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入账户");
                    return;
                }
                String psd = mEtPsd.getText().toString();
                if (TextUtils.isEmpty(psd)) {
                    showToast("请输入密码");
                    return;
                }
                HttpHelperUser.getInstance().userLogin(String.format("%08d", Long.parseLong(phone)), psd, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<UserEntity>() {
                    @Override
                    public void onNext(UserEntity userEntity) {
                        UserManager.getInstance().updateUser((Activity) context, userEntity, true);
                        launch(TabAct.class);
                        finish();
                    }
                }));
                break;
        }
    }
    //10148953  123456
    //00000329  155012
}
