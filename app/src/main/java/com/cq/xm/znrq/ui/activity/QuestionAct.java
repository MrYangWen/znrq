package com.cq.xm.znrq.ui.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.Entity;
import com.cq.xm.znrq.ui.ExpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 常见问题
 * Created by JackMar on 2017/6/16.
 * 邮箱：1261404794@qq.com
 */

public class QuestionAct extends BaseActivity {


    @Bind(R.id.exp_list)
    ExpandableListView mExpList;

    private ExpAdapter expAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.act_question;
    }

    @Override
    protected void initView() {
        List<Entity> list = new ArrayList<>();
        list.add(new Entity("用户充值App如何安装？", new Entity.Child("需要使用App充值的用户，可根据燃气公司的提示，在燃气公司缴费营业厅或指定地方使用手机扫描二维码进行充值App软件下载、安装并打开使用。")));
        list.add(new Entity("用户如何登录充值App软件系统？", new Entity.Child("用户安装好充值App软后打开，系统自动进入登录界面，在指定位置输入用户编号（如1024），然后输入登录密码（初始密码为开户存档身份证号码的后6位），最后点登录进入充值页面。")));
        list.add(new Entity("用户如何完成充值？", new Entity.Child("用户登录进入充值页面后，系统自动显示登录用户信息，用户只需根据实际用气需求填入购买量，然后点“充值”进入订单支付页面，选择或默认使用允许的支付方式后点击“立即支付”，系统自动跳转至确认付款界面，用户自行选择绑定的付款方式，然后点“立即付款”并输入支付密码，系统自动完成订单款项支付。")));
        list.add(new Entity("用户如何查看订单详情？", new Entity.Child("点击充值主页面下部的“订单”即可自动显示用户的全部充值订单，点击任何一笔订单可查看该订单的详细情况。")));
        list.add(new Entity("如何处理未完成支付的订单？", new Entity.Child("对未完成支付的订单，用户可根据需要自行取消订单或继续支付，订单完成支付后的订单不可取消。")));
        list.add(new Entity("用手机充值完成后如何快速将充值量上表？", new Entity.Child("充值支付完成后，如果急需充值上表用气，请长按“查询”键5秒，待蜂鸣器“滴滴”鸣叫2声后放开，待信号灯不闪烁（约3-5分钟）后短按“查询”键查看余量变化，确认充值上表是否成功。上表不成功可重复上述方法。")));
        list.add(new Entity("手机充值支付完成后气量上表不成功怎么办？", new Entity.Child("充值支付完成后，正常情况24小时内可以上表，如果超过48小时充值量未上表，请长按“查询”键5秒，待蜂鸣器“滴滴”鸣叫2声后放开，5分钟后短按“查询”键查看余量变化，确认充值上表是否成功，如果充值量还是没有上表，用户可带上充值卡片前往营业厅登记处理。")));
        expAdapter = new ExpAdapter(list, context, mExpList);
        mExpList.setAdapter(expAdapter);
        mExpList.setGroupIndicator(null);
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
