package com.cq.xm.znrq.ui.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseFragment;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.ui.activity.AboutUsAct;
import com.cq.xm.znrq.ui.activity.QuestionAct;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 */

public class MineFg extends BaseFragment {


    @Bind(R.id.img_head)
    ImageView mImgHead;
    @Bind(R.id.tv_dh)
    TextView mTvDh;

    private UserEntity user = UserManager.getInstance().getUser();

    @Override
    protected void initView() {
        if (user != null) {
            mTvDh.setText(user.getAccount().getUserName());
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_mine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.img_head, R.id.tv_cjwt, R.id.tv_gx, R.id.tv_gy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                break;
            case R.id.tv_cjwt:
                launch(QuestionAct.class);
                break;
            case R.id.tv_gx:
                break;
            case R.id.tv_gy:
                launch(AboutUsAct.class);
                break;
        }
    }
}
