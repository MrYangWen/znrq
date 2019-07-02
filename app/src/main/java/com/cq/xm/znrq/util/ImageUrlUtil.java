package com.cq.xm.znrq.util;

/**
 * 图片URl拼接工具
 * 针对于阿里云OSS图片地址拼接
 * Created by JackMar on 2016/12/7.
 * 邮箱：1261404794@qq.com
 */

public class ImageUrlUtil {

    private static String resizeUrl = "?x-oss-process=image/resize,";

    /**
     * 拼接图片裁剪url
     *
     * @param url
     * @param w
     * @param h
     * @return
     */
    public static String getReSizeImageUrl(String url, int w, int h) {
//        http://cdn.image.canspring.com/20161206/banner1.png?x-oss-process=image/resize,w_400
        StringBuilder imageUrl = new StringBuilder("");
        if (!StrUtil.isEmpty(url)) {
            imageUrl.append(url);
            imageUrl.append(resizeUrl);
            if (w > 0) {
                imageUrl.append("w_" + w);
            }
            if (h > 0) {
                imageUrl.append(",h_" + h);
            }

        }
        return imageUrl.toString();
    }

    /**
     * 拼接图片裁剪url
     *
     * @param url
     * @param w
     * @return
     */
    public static String getReSizeImageUrlW(String url, int w) {
//        http://cdn.image.canspring.com/20161206/banner1.png?x-oss-process=image/resize,w_400
        StringBuilder imageUrl = new StringBuilder("");
        if (!StrUtil.isEmpty(url)) {
            imageUrl.append(url);
            imageUrl.append(resizeUrl);
            if (w > 0) {
                imageUrl.append("w_" + w);
            }

        }
        return imageUrl.toString();
    }

    /**
     * 拼接图片裁剪url
     *
     * @param url
     * @param h
     * @return
     */
    public static String getReSizeImageUrlH(String url, int h) {
//        http://cdn.image.canspring.com/20161206/banner1.png?x-oss-process=image/resize,w_400
        StringBuilder imageUrl = new StringBuilder("");
        if (!StrUtil.isEmpty(url)) {
            imageUrl.append(url);
            imageUrl.append(resizeUrl);
            if (h > 0) {
                imageUrl.append("h_" + h);
            }

        }
        return imageUrl.toString();
    }


    /**
     * 加载头像
     *
     * @param url
     * @return
     */
    public static String getHeadReSizeImageUrl(String url) {
//        http://cdn.image.canspring.com/20161206/banner1.png?x-oss-process=image/resize,w_400
        StringBuilder imageUrl = new StringBuilder("");
        if (!StrUtil.isEmpty(url)) {
            imageUrl.append(url);
            imageUrl.append(resizeUrl);
            imageUrl.append("w_200,h_200");

        }
        return imageUrl.toString();
    }
}
