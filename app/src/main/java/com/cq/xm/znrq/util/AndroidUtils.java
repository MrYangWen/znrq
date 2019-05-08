package com.cq.xm.znrq.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by JackMar on 2017/6/27.
 * 邮箱：1261404794@qq.com
 */

public class AndroidUtils {

    public static void call(String mobile, Context context) {
        if (!StrUtil.isEmpty(mobile)) {
            // 检查是否获得了权限（Android6.0运行时权限）
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // 没有获得授权，申请授权
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.CALL_PHONE)) {
                    // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                    // 弹窗需要解释为何需要该权限，再次请求授权
                    Toast.makeText(context, "请授权！", Toast.LENGTH_LONG).show();

                    // 帮跳转到该应用的设置界面，让用户手动授权
                    Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                    intent1.setData(uri);
                    context.startActivity(intent1);
                } else {
                    // 不需要解释为何需要该权限，直接请求授权
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                }
            } else {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
                context.startActivity(intent);
            }
        } else {
            ToastUtil.getInstance().showToast(context, "电话号码错误");
        }
    }

    /**
     * get App versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "V" + versionName;
    }
}
