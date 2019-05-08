package com.cq.xm.znrq.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.OrderCmdEntity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.service.BluetoothListAct;
import com.cq.xm.znrq.service.BtXiMeiService;
import com.cq.xm.znrq.service.StrToHex;
import com.cq.xm.znrq.util.LogUtil;
import com.cq.xm.znrq.view.TemplateTitle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：{大鹏} on 2017/10/17 14:35
 * 邮箱：919142784@qq.com
 * 页面名称：蓝牙写卡
 */
public class WriteCardAct extends BaseActivity {
    @Bind(R.id.tt_title)
    TemplateTitle mTtTitle;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_bm)
    TextView mTvBm;

    private UserEntity userEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.act_write_card;
    }

    @Override
    protected void initView() {
        mTtTitle.setTitleText("蓝牙写卡");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String backmsg = intent.getStringExtra("backmsg").trim();
                HttpHelperUser.getInstance().Report(BluetoothListAct.id, backmsg, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<BaseResult>() {
                    @Override
                    public void onNext(BaseResult baseResult) {
                        showToast(baseResult.getMessage());
                    }
                }));

            }
        }, new IntentFilter("android.intent.WriteCard"));
    }

    @Override
    protected void initData() {
        userEntity = UserManager.getInstance().getUser();
        if (userEntity != null) {
            mTvName.setText("用户名 :" + userEntity.getAccount().getUserName());
            mTvBm.setText("用户编码 :" + userEntity.getAccount().getUserCode());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.paymentbt)
    public void onViewClicked() {
        OrderCmdEntity entity = getIntent().getParcelableExtra("data");
        String password = entity.getCard().getPassword();
        String paymentmsg = entity.getCard().getContent(); //需API返回
        LogUtil.e("接口返回的写卡参数----" + password + "---password----\n" + paymentmsg);
        String crcmsg = StrToHex.ToHexString(password + password + 30 + paymentmsg);
        String ordermsg = "WCWPSW[" + password + password + 30 + paymentmsg + crcmsg + "]\r";
        startService(new Intent(context, BtXiMeiService.class).putExtra("order", ordermsg));
    }
}
