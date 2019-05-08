package com.cq.xm.znrq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.view.dialog.CommonDialog;
import com.cq.xm.znrq.view.dialog.interf.ICommonDialogCallBack;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：{大鹏} on 2017/9/11 10:05
 * 邮箱：919142784@qq.com
 * 页面名称：维护
 */
public class RepairAct extends BaseActivity {


    private CommonDialog dialog;

    private UserEntity userEntity = UserManager.getInstance().getUser();

    @Override
    protected int getLayoutId() {
        return R.layout.act_reoair;
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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_kf, R.id.tv_gf, R.id.tv_aqjx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_kf:
                dialog = CommonDialog.newInstance("提示:请勿随意进行开关阀处理,否则将影响你正常用气。", "你确定要开阀吗?", "取消", "确定");
                dialog.show(getSupportFragmentManager(), "");
                dialog.setICommonDialogCallback(new ICommonDialogCallBack() {
                    @Override
                    public void onPositiveCase(String tag) {

                    }

                    @Override
                    public void onNegativeCase(String tag) {
                        HttpHelperUser.getInstance().WhKf(String.valueOf(userEntity.getAccount().getMeterId()), new ProgressSubscriber<BaseResult>(context, new IOnNextListener<BaseResult>() {
                            @Override
                            public void onNext(BaseResult baseResult) {
                                showToast(baseResult.getMessage());
                            }
                        }));
                    }
                });
                break;
            case R.id.tv_gf:
                dialog = CommonDialog.newInstance("提示:请勿随意进行开关阀处理,否则将影响你正常用气。", "你确定要关阀吗?", "取消", "确定");
                dialog.show(getSupportFragmentManager(), "");
                dialog.setICommonDialogCallback(new ICommonDialogCallBack() {
                    @Override
                    public void onPositiveCase(String tag) {

                    }

                    @Override
                    public void onNegativeCase(String tag) {
                        HttpHelperUser.getInstance().WhGf(String.valueOf(userEntity.getAccount().getMeterId()), new ProgressSubscriber<BaseResult>(context, new IOnNextListener<BaseResult>() {
                            @Override
                            public void onNext(BaseResult baseResult) {
                                showToast(baseResult.getMessage());
                            }
                        }));
                    }
                });
                break;
            case R.id.tv_aqjx:
                startActivity(new Intent(context, adminRepairAct.class));
                break;
        }
    }

}
