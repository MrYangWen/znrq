package com.cq.xm.znrq.ui.fg;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseFragment;
import com.cq.xm.znrq.bean.OrderEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.ui.activity.OrderDetailAct;
import com.cq.xm.znrq.view.rvlist.IOnRefreshListener;
import com.cq.xm.znrq.view.rvlist.RefreshUtil;
import com.cq.xm.znrq.view.rvlist.view.JRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单
 */

public class OrderFg extends BaseFragment implements IOnRefreshListener {

    @Bind(R.id.listView)
    JRecyclerView mListView;

    private RefreshUtil refreshUtil;

    private CommonAdapter adapter;

    private List<OrderEntity.ItemsBean> list = new ArrayList<>();


    private int pageNum = 1;

    @Override
    protected void initView() {
        refreshUtil = new RefreshUtil(context, mListView);
        refreshUtil.setDefaultDivider();
        refreshUtil.setOnRefreshListener(this);
        adapter = new CommonAdapter<OrderEntity.ItemsBean>(context, R.layout.cell_oder, list) {

            @Override
            protected void convert(ViewHolder holder, OrderEntity.ItemsBean bean, int position) {

                if (bean.getPayType().equals("04")) {//  04支付宝  05微信
                    holder.setBackgroundRes(R.id.img_pay_type, R.mipmap.alipay);
                } else if (bean.getPayType().equals("05")) {
                    holder.setBackgroundRes(R.id.img_pay_type, R.mipmap.wechat);
                } else {
                    holder.setBackgroundRes(R.id.img_pay_type, R.mipmap.cash);
                }
                String paystate = "";
                switch (bean.getPayStat()) {
                    case "00":
                        paystate = "等待付款";
                        break;
                    case "01":
                        paystate = "付款成功";
                        break;
                    case "02":
                        paystate = "付款失败";
                        break;
                    case "03":
                        paystate = "取消付款";
                        break;
                    case "04":
                        paystate = "付款完成";
                        break;

                }
                holder.setText(R.id.tv_state, "充值-" + paystate);
                holder.setText(R.id.tv_time, bean.getPayDate());
                holder.setText(R.id.tv_money, "+ " + bean.getAddAmount());
            }

        };
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, final int position) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("data",list.get(position));
                launch(OrderDetailAct.class,bundle);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        getDataList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_team;
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

    @Override
    public void onRefresh() {
        pageNum = 1;
        getDataList();
    }

    @Override
    public void onLoad() {
        pageNum++;
        getDataList();
    }

    private void getDataList() {
        HttpHelperUser.getInstance().CzJl(UserManager.getInstance().getUserId(), pageNum, new ProgressSubscriber<BaseResult>(context, false, refreshUtil, new IOnNextListener<OrderEntity>() {
            @Override
            public void onNext(OrderEntity entity) {
                if (pageNum == 1) {
                    list.clear();
                }
                list.addAll(entity.getItems());
                adapter.notifyDataSetChanged();
            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        pageNum = 1;
        getDataList();
    }
}
