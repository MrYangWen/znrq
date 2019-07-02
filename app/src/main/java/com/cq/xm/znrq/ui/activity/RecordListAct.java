package com.cq.xm.znrq.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.RecordEntity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
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
 * 作者：{大鹏} on 2017/9/7 15:10
 * 邮箱：919142784@qq.com
 * 页面名称：用气信息
 */
public class RecordListAct extends BaseActivity implements IOnRefreshListener {
    @Bind(R.id.listView)
    JRecyclerView mListView;

    private RefreshUtil refreshUtil;

    private CommonAdapter adapter;

    private List<RecordEntity.ItemsBean> list = new ArrayList<>();

    private UserEntity userEntity = UserManager.getInstance().getUser();

    private int pageNum = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.act_record_list;
    }

    @Override
    protected void initView() {
        refreshUtil = new RefreshUtil(context, mListView);
        refreshUtil.setDefaultDivider();
        refreshUtil.setOnRefreshListener(this);
        adapter = new CommonAdapter<RecordEntity.ItemsBean>(context, R.layout.cell_record, list) {

            @Override
            protected void convert(ViewHolder holder, RecordEntity.ItemsBean bean, int position) {
                holder.setText(R.id.tv_time, "抄表时间: " + bean.getCbsj());
                holder.setText(R.id.tv_qbbh, "气表编号: " + bean.getQbbh());
                holder.setText(R.id.tv_syje, "剩余金额: " + bean.getSurplusFee());
                holder.setText(R.id.tv_ljje, "累计金额: " + bean.getUserFee());
                holder.setText(R.id.tv_syql, "剩余气量: " + bean.getFreeQbll());
                holder.setText(R.id.tv_ljql, "累计气量: " + bean.getQbll());
                if(bean.getOrderNum()>bean.getPayNum()){
                    holder.setText(R.id.tv_gqcs, "购买次数: " + bean.getOrderNum());
                }else{
                    holder.setText(R.id.tv_gqcs, "购买次数: " + bean.getPayNum());
                }
                holder.setText(R.id.tv_xhqd, "网络信号强度: " + bean.getNetwork());
                holder.setText(R.id.tv_js, "当前阶数: " + bean.getFeeSetup());
                String tapStatus="";
                switch (bean.getTapStatus()){
                    case "00":
                        tapStatus="关阀";
                        break;
                    case "01":
                        tapStatus="开阀";
                        break;
                    case "02":
                        tapStatus="异常";
                        break;
                }
                holder.setText(R.id.tv_fmzt, "阀门状态: " +tapStatus);
            }
        };
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("time", list.get(position).getCbsj());
//                bundle.putString("q", "累计气量: "+list.get(position).getQbll());
//                launch(RecordDetailAct.class, bundle);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
        HttpHelperUser.getInstance().Yqjl(String.valueOf(userEntity.getAccount().getMeterId()), pageNum, new ProgressSubscriber<BaseResult>(context, false, refreshUtil, new IOnNextListener<RecordEntity>() {
            @Override
            public void onNext(RecordEntity entity) {
                if (pageNum == 1) {
                    list.clear();
                }
                list.addAll(entity.getItems());
                adapter.notifyDataSetChanged();
            }
        }));
    }
}
