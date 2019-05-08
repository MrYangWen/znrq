package com.cq.xm.znrq.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.BluetoothEntity;
import com.cq.xm.znrq.bean.OrderCmdEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.ui.activity.WriteCardAct;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：{大鹏} on 2017/10/17 10:10
 * 邮箱：919142784@qq.com
 * 页面名称：蓝牙列表
 */
public class BluetoothListAct extends BaseActivity {


    @Bind(R.id.lv_list)
    ListView mLvList;
    @Bind(R.id.start_seach)
    Button mStartSeach;

    private BluetoothAdapter bluetoothAdapter;


    private ArrayList<BluetoothEntity> list = new ArrayList<>();
    private CommonAdapter adapter;

    private BroadcastReceiver broadcastReceiver;
    public static String id;

    @Override
    protected int getLayoutId() {
        return R.layout.act_bluet_list;
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra("id");
        adapter = new CommonAdapter<BluetoothEntity>(context, R.layout.cell_blue_device, list) {

            @Override
            protected void convert(ViewHolder holder, BluetoothEntity item, int position) {
                holder.setText(R.id.tv_name, item.getName() + "\n" + item.getAddress());
            }
        };
        mLvList.setAdapter(adapter);
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showWaitDialog("正在连接蓝牙", true, "");
                BluetoothMsg.BlueToothAddress = list.get(i).getAddress();
                BluetoothMsg.isOpen = false;
                BluetoothMsg.serviceOrCilent = BluetoothMsg.ServerOrCilent.NONE;
                if (BluetoothMsg.lastblueToothAddress != BluetoothMsg.BlueToothAddress) {
                    BluetoothMsg.lastblueToothAddress = BluetoothMsg.BlueToothAddress;
                }
                startService(new Intent(context, BtXiMeiService.class).putExtra("order", "ConnectCommond"));
            }
        });
        initBluetooth();
    }

    @Override
    protected void initData() {
        broadcastReceiver = new BTdataReceiver();  //接受抄表信息广播消息
        registerReceiver(broadcastReceiver, new IntentFilter("android.intent.BlutoothOK"));
        registerReceiver(broadcastReceiver, new IntentFilter("android.intent.BTdata"));

        // 注册用以接收到已搜索到的蓝牙设备的receiver
        registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        // 注册搜索完时的receiver
        registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 获得已经搜索到的蓝牙设备
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 搜索到的不是已经绑定的蓝牙设备
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    list.add(new BluetoothEntity(device.getName(), device.getAddress()));
                    adapter.notifyDataSetChanged();
                }
                // 搜索完成
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                dismissDialog();
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegsiterRe();
    }

    /**
     * 初始化蓝牙
     */
    private void initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            showToast("设备不支持蓝牙连接");
            return;
        }
        // 如果用户的设备没有开启蓝牙，则弹出开启蓝牙设备的对话框，让用户开启蓝牙
        if (!bluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), RESULT_FIRST_USER);
            Intent in = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            in.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 200);
            startActivity(in);
            bluetoothAdapter.enable();
        } else {
            findDevicesList();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK: // 打开蓝牙成功
                showToast("打开蓝牙成功");
                findDevicesList();
                adapter.notifyDataSetChanged();
                break;
            case RESULT_CANCELED: //放弃打开蓝牙
                break;
        }
    }


    /**
     * 获取所有已经绑定的蓝牙设备
     */
    private void findDevicesList() {
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        if (devices.size() > 0) {
            list.add(new BluetoothEntity("已配对设备", ""));
            for (Iterator<BluetoothDevice> it = devices.iterator(); it.hasNext(); ) {
                BluetoothDevice device = it.next();
                list.add(new BluetoothEntity(device.getName(), device.getAddress()));
            }
            list.add(new BluetoothEntity("未配对设备", ""));
        } else {
            list.add(new BluetoothEntity("未配对设备", ""));
        }
    }

    @OnClick(R.id.start_seach)
    public void onClick() {
        list.clear();
        showWaitDialog("正在搜索蓝牙", true, "");
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery(); // 如果正在搜索，就先取消搜索
        }
        bluetoothAdapter.startDiscovery();// 开始搜索蓝牙设备,搜索到的蓝牙设备通过广播返回
        findDevicesList();
    }

    //接受抄表信息广播消息
    private class BTdataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.BTdata")) {
                int gasValume = 0;
                String backmsg = intent.getStringExtra("backmsg");
                if (backmsg.length() < 410) return;
                String userid = backmsg.substring(8, 16);
                String czcs = backmsg.substring(88, 92);
                String gas = backmsg.substring(48, 56);
                String StateFlag = "";
                try {
                    gasValume = Integer.parseInt(new ToInverted().toinverted(gas), 16);
                    gasValume = gasValume / 10;
                    if (gasValume == 0)
                        StateFlag = "已输表";
                    else
                        StateFlag = "未输表(请先充值入表再购气)";
                } catch (Exception e) {
                    e.toString();
                }
                HttpHelperUser.getInstance().lyCmd(id, backmsg, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<OrderCmdEntity>() {
                    @Override
                    public void onNext(OrderCmdEntity entity) {
                        startActivity(new Intent(context, WriteCardAct.class).putExtra("data", entity));
                    }
                }));
            } else if (action.equals("android.intent.BlutoothOK")) {
                if (intent.getStringExtra("backmsg").equals("FAILED")) {
                    showToast("蓝牙连接失败");
                } else {
                    showToast("蓝牙连接成功");
                    String ordermsg = "READ[30CF" + "FF" + "]\r";
                    startService(new Intent(context, BtXiMeiService.class).putExtra("order", ordermsg));
                }
                dismissDialog();
            }
        }
    }
}
