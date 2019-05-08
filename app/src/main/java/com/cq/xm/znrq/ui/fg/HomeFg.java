package com.cq.xm.znrq.ui.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseFragment;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.ui.activity.AddMoneyAct;
import com.cq.xm.znrq.ui.activity.RecordListAct;
import com.cq.xm.znrq.ui.activity.RepairAct;
import com.cq.xm.znrq.util.ScreenUtil;
import com.cq.xm.znrq.util.ToastUtil;
import com.cq.xm.znrq.util.ViewUtil;
import com.cq.xm.znrq.view.NoScrollGridView;
import com.cq.xm.znrq.view.banner.StoreBanner;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */

public class HomeFg extends BaseFragment {

    @Bind(R.id.cb_banner)
    ConvenientBanner mCbBanner;
    @Bind(R.id.gv)
    NoScrollGridView mGv;
    @Bind(R.id.tv_ad)
    TextView mTvAd;


    private ArrayList<Integer> list = new ArrayList<Integer>();


    @Override
    protected void initView() {
        ViewUtil.setViewSizeh(mCbBanner, (int) (ScreenUtil.defaultCenter().getWidth() * 0.6f));

//        //西美
        list.add(R.drawable.bn1);
        list.add(R.drawable.bn2);
        list.add(R.drawable.bn3);

        //恩施
//        list.add(R.drawable.es_1);
//        list.add(R.drawable.es_2);
//        list.add(R.drawable.es_3);
//        list.add(R.drawable.es_4);
//        list.add(R.drawable.es_5);
//        list.add(R.drawable.es_6);


        mCbBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new StoreBanner();
            }
        }, list).setPageIndicator(new int[]{R.mipmap.xq_baise, R.mipmap.xq_yq}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).notifyDataSetChanged();
        mCbBanner.setCanLoop(true);
        mCbBanner.startTurning(5000);
        initGridView();
    }


    private CommonAdapter<String> adapter;

    //用户
    private List<String> lType = Arrays.asList("燃气充值", "用气信息", "表具管理", "服务网点", "安全用气", "咨询留言");
    private int[] iconTypes = {R.mipmap.home_icon1, R.mipmap.home_icon2, R.mipmap.home_icon3, R.mipmap.home_icon4, R.mipmap.home_icon5, R.mipmap.home_icon6};


    //管理员
    //private List<String> lType = Arrays.asList("燃气充值", "用气信息", "表具管理", "服务网点", "安全用气", "咨询留言", "维护");
    //private int[] iconTypes = {R.mipmap.home_icon1, R.mipmap.home_icon2, R.mipmap.home_icon3, R.mipmap.home_icon4, R.mipmap.home_icon5, R.mipmap.home_icon6, R.mipmap.sy_wh};


    private void initGridView() {
        adapter = new CommonAdapter<String>(context, R.layout.item_home, lType) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.tv, item);
                viewHolder.setImageResource(R.id.iv, iconTypes[position]);
            }
        };
        mGv.setAdapter(adapter);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (UserManager.getInstance().checkLogin(context, true)) {
                    ToastUtil.getInstance().showToast(context, lType.get(position));
                    switch (position) {
                        case 0://燃气充值
                            launch(AddMoneyAct.class);
                            break;
                        case 1://用气信息
                            launch(RecordListAct.class);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            launch(RepairAct.class);
                            break;
                    }
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_score;
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
}
