package com.cq.xm.znrq.http.constant;

import android.text.TextUtils;

import com.cq.xm.znrq.util.PreHelper;
import com.cq.xm.znrq.util.PreferenceKey;

/**
 * Created by JackMar on 2017/2/24.
 * 邮箱：1261404794@qq.com
 */

public class NetDefine {

    static NetDefine netDefine = new NetDefine();

    public synchronized static NetDefine getInstance() {
        return netDefine;
    }


    public static String HostUrl = "http://39.108.128.104:8080";  //巴中利民燃气
        //public static String HostUrl = "https://222.180.163.205:8075";  //西美燃气
    //    private static final String HostUrl = "http://222.180.162.247:8085/";//平遥的IP和充值端口
    //    private static final String HostUrl = "http://150.138.119.224:8085";//东营
    //    private static final String HostUrl = "http://58.49.235.161:8090/";//恩施
    //    private static final String HostUrl = "http://114.116.3.21:8090/";//莘县通达
     //   private static final String HostUrl = "http://36.111.176.21:8090/";//贵州新兴燃气
//    private static final String HostUrl = "http://222.180.162.247:8090/";//开县
//    private static final String HostUrl = "http://www.samrtcloud.com";//
    //private static final String HostUrl = "http://124.226.216.113:8686";//河池
//    private static final String HostUrl = "http://222.180.162.247:9055";//山东广饶
    public static final boolean DEBUG = true;

    public String getUrl() {
        String url = PreHelper.defaultCenter().getStringData(PreferenceKey.BASE_URL);
        if (TextUtils.isEmpty(url)) {
            url = HostUrl;
        }
        return url;
    }
}
