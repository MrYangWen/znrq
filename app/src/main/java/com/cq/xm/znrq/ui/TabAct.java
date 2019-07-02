package com.cq.xm.znrq.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.XApplication;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.ui.fg.CompanyFg;
import com.cq.xm.znrq.ui.fg.MineFg;
import com.cq.xm.znrq.ui.fg.HomeFg;
import com.cq.xm.znrq.ui.fg.OrderFg;
import com.cq.xm.znrq.view.statusbarutiil.StatusBarUtil;
import com.cq.xm.znrq.view.tab.TabMenuView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */

public class TabAct extends BaseActivity {
    @Bind(R.id.tmv_menu)
    TabMenuView mTmvMenu;

    private HomeFg homeFg;
    private OrderFg orderFg;
    private CompanyFg companyFg;
    private MineFg mineFg;

    private long firstTime = 0;//记录退出按键时间

    @Override
    protected int getLayoutId() {
        return R.layout.act_tab;
    }

    @Override
    protected void initView() {
        regsiterRe();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (homeFg == null) {
            homeFg = new HomeFg();
        }
        fragmentTransaction.add(R.id.fl_content, homeFg);
        fragmentTransaction.commit();
        mTmvMenu.setDelegate(new TabMenuView.onPageSeletedDelegate() {
            @Override
            public void onPageSelected(int page) {
                initFragment(page);
                mTmvMenu.setPage(page);
                XApplication.currentPageIndex = page;
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setStatusBar() {
        //fragment里面的布局
        StatusBarUtil.setTranslucentForImageViewInFragment(TabAct.this, 0, null);
    }

    /**
     * 初始化fragment
     *
     * @param page
     */
    private void initFragment(int page) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (page) {
            case 0:
                if (homeFg == null) {
                    homeFg = new HomeFg();
                    fragmentTransaction.add(R.id.fl_content, homeFg);
                } else {
                    fragmentTransaction.show(homeFg);
                }
                break;
            case 1:
                UserManager.getInstance().checkLogin(context, true);
                if (orderFg == null) {
                    orderFg = new OrderFg();
                    fragmentTransaction.add(R.id.fl_content, orderFg);
                } else {
                    fragmentTransaction.show(orderFg);
                }
                break;
            case 2:
                UserManager.getInstance().checkLogin(context, true);
                if (mineFg == null) {
                    mineFg = new MineFg();
                    fragmentTransaction.add(R.id.fl_content, mineFg);
                } else {
                    fragmentTransaction.show(mineFg);
                }
                break;
            case 3:
                UserManager.getInstance().checkLogin(context, true);
                if (mineFg == null) {
                    mineFg = new MineFg();
                    fragmentTransaction.add(R.id.fl_content, mineFg);
                } else {
                    fragmentTransaction.show(mineFg);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFg != null) {
            transaction.hide(homeFg);
        }
        if (orderFg != null) {
            transaction.hide(orderFg);
        }
        if (companyFg != null) {
            transaction.hide(companyFg);
        }
        if (mineFg != null) {
            transaction.hide(mineFg);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
