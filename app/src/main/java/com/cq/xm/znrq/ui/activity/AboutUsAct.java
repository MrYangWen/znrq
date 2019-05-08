package com.cq.xm.znrq.ui.activity;

import android.os.Bundle;


import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 关于
 * Created by JackMar on 2017/6/16.
 * 邮箱：1261404794@qq.com
 */

public class AboutUsAct extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.act_about_us;
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
}
