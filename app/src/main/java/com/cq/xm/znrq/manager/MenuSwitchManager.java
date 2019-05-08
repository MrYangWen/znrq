package com.cq.xm.znrq.manager;

/**
 * Created by JackMar on 2017/6/9.
 * 邮箱：1261404794@qq.com
 */

public class MenuSwitchManager {
    static MenuSwitchManager single = null;
    public synchronized static MenuSwitchManager getInstance() {
        if (single == null) {
            single = new MenuSwitchManager();
        }
        return single;
    }
}
