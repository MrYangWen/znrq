package com.cq.xm.znrq.wxapi;


import android.content.Intent;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.view.dialog.PaySuccessDialog;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
//    67:98:4C:F9:81:E5:75:07:B0:BC:20:0C:41:D1:11:A8

    public static final String APP_ID = "wxd94c48e1dcc072c5";
    private IWXAPI api;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.errCode == 0) {
//            PaySuccessDialog paySuccessDialog = new PaySuccessDialog();
//            paySuccessDialog.show(getSupportFragmentManager(), "");
        } else {
            showToast("支付失败" + resp.errCode);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_pay_success;
    }

    @Override
    protected void initView() {
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void initData() {

    }
}