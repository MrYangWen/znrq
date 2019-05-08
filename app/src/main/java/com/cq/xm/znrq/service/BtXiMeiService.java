package com.cq.xm.znrq.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.cq.xm.znrq.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BtXiMeiService extends Service {

    public final int MSG_READ = 1;
    public final int MSG_WRITE = 2;
    int conflag = 0;
    private BluetoothServerSocket mserverSocket = null;
    private clientThread clientConnectThread = null;
    private BluetoothDevice device = null;
    private readThread mreadThread = null;
    ;
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    BluetoothAdapter btAdapt;
    private BluetoothSocket mmSocket;
    private String[] rowdata;
    // private BluetoothDevice mmDevice;
    BluetoothDevice btDev;
    final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    UUID uuid;
    private String order;
//	private CRC crc;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(Connectstate, intent);
    }

    //开启客户端
    private class clientThread extends Thread {
        @Override
        public void run() {
            try {
                //创建一个Socket连接：只需要服务器在注册时的UUID号
                // socket = device.createRfcommSocketToServiceRecord(BluetoothProtocols.OBEX_OBJECT_PUSH_PROTOCOL_UUID);
                mmSocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                //连接
                Message msg2 = new Message();
                // msg2.obj = "请稍候，正在连接服务器:"+BluetoothMsg.BlueToothAddress;
                // msg2.what = 0;
                // LinkDetectedHandler.sendMessage(msg2);
                mmSocket.connect();
                Message msg = new Message();
                msg.obj = "已经连接上服务端！可以发送信息。";
                msg.what = 11;
                handler.sendMessage(msg);
                //启动接受数据
                mreadThread = new readThread();
                mreadThread.start();
            } catch (IOException e) {
                Log.e("connect", "", e);
                Message msg = new Message();
                msg.obj = "连接服务端异常！断开连接重新试一试。";
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }
    }

    ;

    /* 停止客户端连接 */
    private void shutdownClient() {
        new Thread() {
            @Override
            public void run() {
                if (clientConnectThread != null) {
                    clientConnectThread.interrupt();
                    clientConnectThread = null;
                }
                if (mreadThread != null) {
                    mreadThread.interrupt();
                    mreadThread = null;
                }
                if (mmSocket != null) {
                    try {
                        mmSocket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mmSocket = null;
                }
            }

            ;
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        order = intent.getStringExtra("order");
        try {
            Log.e("test", "cardwrite");
            if (order != null) {
                if (order.equals("ConnectCommond")) {
                    String address = BluetoothMsg.BlueToothAddress;
                    if (address != null) {
                        //shutdownClient();
                        if (clientConnectThread != null) {
                            clientConnectThread.interrupt();
                            clientConnectThread = null;
                        }
                        if (mreadThread != null) {
                            mreadThread.interrupt();
                            mreadThread = null;
                        }
                        if (mmSocket != null) {
                            try {
                                mmSocket.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            mmSocket = null;
                        }
                        device = mBluetoothAdapter.getRemoteDevice(address);
                        clientConnectThread = new clientThread();
                        clientConnectThread.start();
                        BluetoothMsg.isOpen = true;
                    }
                } else {
                    writer(order);
                }
            }

        } catch (Exception e) {
            Log.e("test", e.toString());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private String  data="";
    // 接收其他线程消息的Handler
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // 处理消息
            switch (msg.what) {
                case MSG_READ:
//                    byte[] backmsg1 = (byte[]) msg.obj;
//                    String backmsg = new String(backmsg1);
//                    LogUtil.e("读写卡返回信息" + "\n" + backmsg);
//                    if (order.contains("WCWPSW")) {
//                        BtXiMeiService.this.sendBroadcast(new Intent("android.intent.WriteCard").putExtra("backmsg", backmsg));
//                    } else {
//                        BtXiMeiService.this.sendBroadcast(new Intent("android.intent.BTdata").putExtra("backmsg", backmsg));
//                    }
                    byte[] backmsg1 = (byte[]) msg.obj;
                    String backmsg = new String(backmsg1);
                    LogUtil.e("读写卡返回信息" + "\n" + backmsg);
                    data+=backmsg;
                    if (data.length()>=416){
                        if (order.contains("WCWPSW")) {
                            BtXiMeiService.this.sendBroadcast(new Intent("android.intent.WriteCard").putExtra("backmsg", data));
                        } else {
                            BtXiMeiService.this.sendBroadcast(new Intent("android.intent.BTdata").putExtra("backmsg", data));
                        }
                        data="";
                    }else if (data.contains("OK")||data.contains("ERROR")){
                        BtXiMeiService.this.sendBroadcast(new Intent("android.intent.WriteCard").putExtra("backmsg", data));
                        data="";
                    }
                    break;
                case MSG_WRITE:
                    break;
                case 6:
                    LogUtil.e("开始连接");
                    break;
                case 7:
                    LogUtil.e("连接成功");
                    new Thread(new readThread()).start();
                    break;
                case 8:
                    LogUtil.e("连接失败");
                    break;
                case 11:
                    Intent btdata1 = new Intent("android.intent.BlutoothOK");
                    btdata1.putExtra("backmsg", "OK");
                    BtXiMeiService.this.sendBroadcast(btdata1);
                    break;
                case 0:
                    Intent btdata = new Intent("android.intent.BlutoothOK");
                    btdata.putExtra("backmsg", "FAILED");
                    BtXiMeiService.this.sendBroadcast(btdata);
                    break;
            }
            super.handleMessage(msg);
        }

    };

    // 广播接收器监听filter事件处理
    private final BroadcastReceiver Connectstate = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
        }
    };

    // 自动8秒一次连接蓝牙模块线程
    public class ATconnect implements Runnable {
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                new Thread(new ConnectThread(btDev)).start();
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (conflag == 1) {

                    break;
                }
            }
        }
    }


    // 作为客户端连接蓝牙线程
    public class ConnectThread implements Runnable {

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            // mmDevice = device;
            try {
                tmp = device.createRfcommSocketToServiceRecord(uuid);
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void run() {
            btAdapt.cancelDiscovery();
            try {
                handler.obtainMessage(6).sendToTarget();
                mmSocket.connect();
                handler.obtainMessage(7, mmSocket).sendToTarget();
                // conflag=1;
            } catch (IOException connectException) {
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                }
                handler.obtainMessage(8).sendToTarget();
                return;
            }
        }
    }

    // 读取数据
    private class readThread extends Thread {
        public void run() {
            Log.e("test", "进入读取数据线程");
            byte[] buffer = new byte[1024];
            int bytes;
            InputStream mmInStream = null;
            try {
                mmInStream = mmSocket.getInputStream();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Log.e("test", "开始循环读取");
            while (true) {
                try {
                    // Read from the InputStream
                    // Log.e("test", "判断多少个字节");
                    if (mmInStream == null) {
                        break;
                    }
                    if ((bytes = mmInStream.read(buffer)) > 0) {
                        // Log.e("test", String.valueOf(bytes));
                        byte[] buf_data = new byte[bytes];
                        for (int i = 0; i < bytes; i++) {
                            buf_data[i] = buffer[i];
                        }
                        // Log.e("test", "发送到ui");
                        String s = new String(buf_data);
                        // Log.e("test", s);
                        Message msg = new Message();
                        msg.obj = buf_data;
                        msg.what = MSG_READ;
                        handler.sendMessage(msg);
                        // Log.e("test", "发送完成");
                    }

                } catch (IOException e) {
                    try {
                        mmInStream.close();

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    // 发送数据
    private void writer(String msg) {
        if (mmSocket == null) {
            return;
        }
        try {
            OutputStream os = mmSocket.getOutputStream();
            os.write(msg.getBytes());
            Log.e("test", "发送模块" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 发送线程
    Runnable r = new Runnable() {
        // @Override
        public void run() {
            // TODO Auto-generated method stub
            Message msg = handler.obtainMessage();
            msg.what = MSG_WRITE;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendMessage(msg);
        }
    };

}
