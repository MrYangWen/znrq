package com.cq.xm.znrq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.OrderCmdEntity;
import com.cq.xm.znrq.bean.OrderEntity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.service.BluetoothListAct;
import com.cq.xm.znrq.view.TemplateTitle;
import com.cq.xm.znrq.view.dialog.CommonDialog;
import com.cq.xm.znrq.view.dialog.interf.ICommonDialogCallBack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：{大鹏} on 2017/9/7 15:10
 * 邮箱：919142784@qq.com
 * 页面名称：用户远程充值
 */
public class OrderDetailAct extends BaseActivity {


    @Bind(R.id.tv_title)
    TemplateTitle mTvTitle;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tv_money)
    TextView mTvMoney;
    @Bind(R.id.tv_dw)
    TextView mTvDw;
    @Bind(R.id.tv_huhao)
    TextView mTvHuhao;
    @Bind(R.id.tv_huming)
    TextView mTvHuming;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.tv_qx)
    TextView mTvQx;
    @Bind(R.id.tv_zf)
    TextView mTvZf;
    @Bind(R.id.tv_writeStat)
    TextView mTvWriteStat;
    @Bind(R.id.tv_orderId)
    TextView mTvOrderId;
    private UserEntity userEntity = UserManager.getInstance().getUser();

    private OrderEntity.ItemsBean bean;

    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.act_order_detail;
    }

    @Override
    protected void initView() {
        bean = getIntent().getParcelableExtra("data");
        mTvHuhao.setText(userEntity.getAccount().getUserCode() + "");
        mTvHuming.setText(userEntity.getAccount().getUserName());
        mTvAddress.setText(userEntity.getAccount().getAddrLocation());
        mTvTime.setText(bean.getPayDate());
        mTvMoney.setText("¥ " + bean.getAddAmount());
        type = userEntity.getAccount().getMeterType();//meterType 20无线表 21无线IC卡表
        if (bean.getPayStat().equals("01")) {
            if (bean.getWriteStat().equals("00")) { //待写卡或者未生成预存指令00 已写卡或者生成预存指令01
                mTvZf.setVisibility(View.VISIBLE);
                mTvZf.setText("蓝牙写卡");
                mTvWriteStat.setText("未充值");
                if (type.equals("20") || type.equals("21")) {
                    mTvQx.setVisibility(View.VISIBLE);
                    mTvQx.setText("远传充值");
                }
            } else {
                mTvWriteStat.setText("已充值");
            }
        } else if (bean.getPayStat().equals("03")) {
            mTvQx.setVisibility(View.GONE);
            mTvZf.setVisibility(View.GONE);
        } else {
            mTvQx.setVisibility(View.VISIBLE);
            mTvZf.setVisibility(View.VISIBLE);
            mTvWriteStat.setText("未付款");
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_qx, R.id.tv_zf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qx:
                if (mTvQx.getText().equals("远传充值")) {
                    HttpHelperUser.getInstance().writepayCmd(String.valueOf(bean.getId()), new ProgressSubscriber<BaseResult>(context, new IOnNextListener<BaseResult>() {
                        @Override
                        public void onNext(BaseResult result) {
                            showToast(result.getMessage());
                        }
                    }));
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long time = 0;
                    try {
                        time = sdf.parse(sdf.format(new Date())).getTime() - sdf.parse(bean.getPayDate()).getTime();
                        if(time<300000 && bean.getPayType().equals("06")){
                            showToast("付款后5分钟内不可取消");
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    CommonDialog dialog = CommonDialog.newInstance("提示", "你确定要取消订单吗?", "取消", "确定");
                    dialog.show(getSupportFragmentManager(), "");
                    dialog.setICommonDialogCallback(new ICommonDialogCallBack() {
                        @Override
                        public void onPositiveCase(String tag) {

                        }

                        @Override
                        public void onNegativeCase(String tag) {
                            //点击删除
                            HttpHelperUser.getInstance().deleteOrder(String.valueOf(bean.getId()), new ProgressSubscriber<BaseResult>(context, new IOnNextListener<BaseResult>() {
                                @Override
                                public void onNext(BaseResult baseResult) {
                                    showToast(baseResult.getMessage());
                                    finish();
                                }
                            }));
                        }
                    });
                }
                break;
            case R.id.tv_zf:
                if (bean.getPayStat().equals("01")) {
                    startActivity(new Intent(context, BluetoothListAct.class).putExtra("id", bean.getId() + ""));
                    finish();
                } else if (bean.getPayStat().equals("00") || bean.getPayStat().equals("03")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("addAmount", bean.getAddAmount());
                    bundle.putString("AddNumber", bean.getAddNumber() + "");
                    bundle.putString("tyep", "1");
                    bundle.putString("id", bean.getId() + "");
                    launch(PayOrderAct.class, bundle);
                }
                break;
        }
    }
}
