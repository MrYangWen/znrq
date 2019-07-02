package com.cq.xm.znrq.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.manager.UserManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：{大鹏} on 2017/9/7 15:10
 * 邮箱：919142784@qq.com
 * 页面名称：用气详情
 */
public class RecordDetailAct extends BaseActivity {

    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tv_money)
    TextView mTvMoney;
    @Bind(R.id.tv_huhao)
    TextView mTvHuhao;
    @Bind(R.id.tv_huming)
    TextView mTvHuming;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.tv_xz)
    TextView mTvXz;

    private UserEntity userEntity = UserManager.getInstance().getUser();

    @Override
    protected int getLayoutId() {
        return R.layout.act_record_detail;
    }

    @Override
    protected void initView() {
        mTvHuhao.setText(userEntity.getAccount().getMeterId() + "");
        mTvHuming.setText(userEntity.getPc().getCrtUserName());
        mTvAddress.setText(userEntity.getAccount().getAddrLocation());
        mTvXz.setText(userEntity.getPc().getPriceName());
    }

    @Override
    protected void initData() {
        String time = getIntent().getStringExtra("time");
        String q = getIntent().getStringExtra("q");
        mTvTime.setText(time);
        mTvMoney.setText(q);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
