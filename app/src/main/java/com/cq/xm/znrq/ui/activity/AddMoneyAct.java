package com.cq.xm.znrq.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.CzHsEntity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：{大鹏} on 2017/8/14 10:47
 * 邮箱：919142784@qq.com
 * 页面名称：充值
 */
public class AddMoneyAct extends BaseActivity {

    @Bind(R.id.tv_hh)
    TextView mTvHh;
    @Bind(R.id.tv_hm)
    TextView mTvHm;
    @Bind(R.id.tv_xz)
    TextView mTvXz;
    @Bind(R.id.tv_xy_money)
    TextView mTvXyMoney;
    @Bind(R.id.et_money)
    EditText mEtMoney;
    @Bind(R.id.tv_cz)
    TextView mTvCz;

    private UserEntity userEntity = UserManager.getInstance().getUser();

    @Override
    protected int getLayoutId() {
        return R.layout.act_addmoney;
    }

    @Override
    protected void initView() {
        if (userEntity != null) {
            mTvHh.setText(userEntity.getAccount().getUserCode() + "");
            mTvHm.setText("" + userEntity.getAccount().getUserName());
            mTvXz.setText("" + userEntity.getPc().getPriceName());
            if (userEntity.getAccount().getVersion().equals("1")) {  // account 里面的version 为1时
                mTvXyMoney.setText("充值金额");
                mEtMoney.setHint("购买金额");
            }
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

    @OnClick(R.id.tv_cz)
    public void onClick() {
        String m = mEtMoney.getText().toString().trim();
        if (TextUtils.isEmpty(m) || m.equals("0")) {
            showToast("请输入购气数量");
            return;
        }
        getPriceUnit(m);
    }


    private void getPriceUnit(String quota) {
        HttpHelperUser.getInstance().hsql(UserManager.getInstance().getUserId(), quota, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<CzHsEntity>() {
            @Override
            public void onNext(CzHsEntity entity) {
                Bundle bundle = new Bundle();
                bundle.putString("addAmount", String.valueOf(entity.getPay().getAddAmount()));
                bundle.putString("AddNumber", String.valueOf(entity.getPay().getAddNumber()));
                launch(PayOrderAct.class, bundle);
            }
        }));
    }
}
