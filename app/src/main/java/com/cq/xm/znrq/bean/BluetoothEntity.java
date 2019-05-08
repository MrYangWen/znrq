package com.cq.xm.znrq.bean;

/**
 * 作者：{大鹏} on 2017/10/17 11:03
 * 邮箱：919142784@qq.com
 * 页面名称：
 */
public class BluetoothEntity {
    private String name;
    private String address;

    public BluetoothEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
