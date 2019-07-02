package com.cq.xm.znrq.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.http.constant.NetDefine;
import com.cq.xm.znrq.util.PreHelper;
import com.cq.xm.znrq.util.PreferenceKey;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UrlSettingAct extends BaseActivity {


    @Bind(R.id.tv_url)
    TextView mTvUrl;
    @Bind(R.id.ModifyIP)
    EditText mModifyIP;
    @Bind(R.id.BMIP)
    Button mBMIP;
    @Bind(R.id.readIP)
    Button mReadIP;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mTvUrl.setText(NetDefine.getInstance().getUrl());
        mModifyIP.setText(NetDefine.getInstance().getUrl());
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

    @OnClick(R.id.BMIP)
    public void onClick() {
        String setUrl = mModifyIP.getText().toString();
        URL url;
        try {
            url = new URL(setUrl);
            PreHelper.defaultCenter().setData(PreferenceKey.BASE_URL, setUrl);
            showToast("修改成功");
            finish();
        } catch (Exception e1) {
            url = null;
            showToast("请输入正确的地址");
        }
    }
}
