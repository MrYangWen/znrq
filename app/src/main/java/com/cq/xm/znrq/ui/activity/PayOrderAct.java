package com.cq.xm.znrq.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cq.xm.znrq.R;
import com.cq.xm.znrq.alipay.PayHelper;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.AddOrderEntity;
import com.cq.xm.znrq.bean.AlPayEntity;
import com.cq.xm.znrq.bean.ICBCBean;
import com.cq.xm.znrq.bean.PayResult;
import com.cq.xm.znrq.bean.PayWxBean;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.util.LogUtil;
import com.cq.xm.znrq.view.dialog.PaySuccessDialog;
import com.icbc.icbcfactory.ICBCPAPIFactory;
import com.icbc.paysdk.ICBCAPI;
import com.icbc.paysdk.constants.Constants;
import com.icbc.paysdk.model.PayReq;

import org.json.JSONException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：{大鹏} on 2017/8/14 10:47
 * 邮箱：919142784@qq.com
 * 页面名称：支付订单
 */
public class PayOrderAct extends BaseActivity {


    @Bind(R.id.tv_money)
    TextView mTvMoney;
    @Bind(R.id.icon_alipay)
    ImageView mIconAlipay;
    @Bind(R.id.cb_alipay)
    CheckBox mCbAlipay;
    @Bind(R.id.rl_alipay)
    LinearLayout mRlAlipay;
    @Bind(R.id.cb_wx)
    CheckBox mCbWx;
    @Bind(R.id.cb_yl)
    CheckBox mCbYl;
    @Bind(R.id.rl_wx)
    LinearLayout mRlWx;
    @Bind(R.id.tv_zf)
    TextView mTvZf;

    private String payType = "05";  // 04支付宝  05微信  06 工商

    private UserEntity userEntity = UserManager.getInstance().getUser();

    private String addAmount;
    private String AddNumber;
    private String tyep = "";
    private String id = "";

    private ICBCPAPIFactory factory;

    @Override
    protected int getLayoutId() {
        return R.layout.act_pay_order;
    }

    @Override
    protected void initView() {
        addAmount = getIntent().getStringExtra("addAmount");
        AddNumber = getIntent().getStringExtra("AddNumber");
        if (getIntent().hasExtra("tyep")) {
            tyep = getIntent().getStringExtra("tyep");
        }
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
        mTvMoney.setText("¥ " + addAmount + "元");
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

    @OnClick({R.id.rl_alipay, R.id.rl_wx, R.id.rl_yl, R.id.tv_zf})
    public void onClick(View view) {
        switch (view.getId()) {
            // 04支付宝  05微信
            case R.id.rl_alipay:
                setCheck(mCbAlipay);
                payType = "04";
                break;
            case R.id.rl_wx:
                setCheck(mCbWx);
                payType = "05";
                break;
            case R.id.rl_yl:
                setCheck(mCbYl);
                payType = "06";
                break;
            case R.id.tv_zf:
                //app下单
                if (tyep.equals("1")) {
                    //未支付-继续支付
                    HttpHelperUser.getInstance().AlPay(id, payType, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<AlPayEntity>() {
                        @Override
                        public void onNext(final AlPayEntity entity) {
                            if (payType.equals("04")) {
                                PayHelper.aliPay(entity.getPayInfo(), context, new Handler(new Handler.Callback() {
                                    @Override
                                    public boolean handleMessage(Message msg) {
                                        PayResult result = new PayResult((String) msg.obj);
                                        if (result.getResultStatus().equals("9000")) {
                                            new PaySuccessDialog().show(getSupportFragmentManager(), "");
                                        } else {
                                            showToast("支付失败");
                                        }
                                        return false;
                                    }
                                }));
                            } else {
                                try {
                                    PayWxBean payWxBean = JSON.parseObject(entity.getPayInfo(), PayWxBean.class);
                                    PayHelper.wechatPay((Activity) context, payWxBean);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }));
                } else {
                    HttpHelperUser.getInstance().addOrder(String.valueOf(userEntity.getAccount().getId()), addAmount, AddNumber, payType, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<AddOrderEntity>() {
                        @Override
                        public void onNext(AddOrderEntity entity) {
                            //生成签名
                            HttpHelperUser.getInstance().AlPay(String.valueOf(entity.getId()), payType, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<AlPayEntity>() {
                                @Override
                                public void onNext(final AlPayEntity entity) {

                                    switch (payType) {
                                        case "04":
                                            PayHelper.aliPay(entity.getPayInfo(), context, new Handler(new Handler.Callback() {
                                                @Override
                                                public boolean handleMessage(Message msg) {
                                                    PayResult result = new PayResult((String) msg.obj);
                                                    if (result.getResultStatus().equals("9000")) {
                                                        new PaySuccessDialog().show(getSupportFragmentManager(), "");
                                                    } else {
                                                        showToast("支付失败");
                                                    }
                                                    return false;
                                                }
                                            }));
                                            break;
                                        case "05":
                                            try {
                                                PayWxBean payWxBean = JSON.parseObject(entity.getPayInfo(), PayWxBean.class);
                                                PayHelper.wechatPay((Activity) context, payWxBean);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case "06":

                                            ICBCBean payWxBean = JSON.parseObject(entity.getPayInfo(), ICBCBean.class);
                                            factory = new ICBCPAPIFactory();
                                            ICBCAPI api = factory.createICBCAPI(context);
                                            PayReq req = new PayReq();
                                            req.setInterfaceName(payWxBean.getInterfaceName());
                                            req.setInterfaceVersion(payWxBean.getInterfaceVersion());
                                            req.setTranData(payWxBean.getTranData());
                                            req.setMerSignMsg(payWxBean.getMerSignMsg());
                                            req.setMerCert(payWxBean.getMerCert());
                                            ///servlet/ICBCINBSEBusinessServlet
                                            Constants.PAY_LIST_IP = "https://b2c.icbc.com.cn";
                                            ///ICBCWAPBank/servlet/ICBCWAPEBizServlet
                                            Constants.Start_B2C_IP ="https://mywap2.icbc.com.cn";
                                            api.sendReq(context, req);
                                            break;
                                    }
                                }
                            }));
                        }
                    }));
                }
                break;
        }
    }

    /**
     * 改变按钮状态
     *
     * @param check
     */
    private void setCheck(CheckBox check) {
        mCbWx.setChecked(false);
        mCbAlipay.setChecked(false);
        mCbYl.setChecked(false);
        check.setChecked(true);
    }

    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        if (factory != null) {
            factory.releaseICBCAPI(context);
        }
    }
}
