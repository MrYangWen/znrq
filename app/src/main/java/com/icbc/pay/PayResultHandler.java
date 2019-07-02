package com.icbc.pay;

import com.alibaba.fastjson.JSON;
import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.util.LogUtil;
import com.icbc.paysdk.IPayEventHandler;
import com.icbc.paysdk.model.PayResp;
import com.icbc.paysdk.model.ReqErr;

/**
 * Created by DP on 2018/7/6.
 */

public class PayResultHandler extends BaseActivity implements IPayEventHandler {

    @Override
    protected int getLayoutId() {
        return R.layout.act_pay_success;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onErr(ReqErr reqErr) {
        LogUtil.e(JSON.toJSONString(reqErr));
    }

    @Override
    public void onResp(PayResp payResp) {
        LogUtil.e(JSON.toJSONString(payResp));

        if(payResp.getTranCode().equals("200")){
            showToast("支付成功");
        }

        if(payResp.getTranCode().equals("201")){
            showToast("支付失败");
        }

    }
}
