package com.cq.xm.znrq.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.cq.xm.znrq.R;

import java.io.File;

/**
 * Glide图片加载
 * 包含有OSS 图片裁剪方法
 * Created by JackMar on 2016/11/16.
 * 邮箱：1261404794@qq.com
 */

public class GlideImageLoadUtil {

    /**
     * 使用glide加载网络图片
     *
     * @param context
     * @param url            图片链接
     * @param imageView      被加载视图
     * @param animRes        动画资源
     * @param placeHolderRes 加载中状态资源
     * @param errorRes       加载失败状态资源
     */
    public static void loadImage(Context context, String url, ImageView imageView, int animRes, int placeHolderRes, int errorRes) {
        Glide.with(context).load(url).asBitmap().placeholder(placeHolderRes).error(errorRes).animate(animRes).into(imageView);
    }


    /**
     * 常规图片加载
     *
     * @param context
     * @param url       图片链接
     * @param imageView 被加载视图
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, R.anim.fade_in, R.mipmap.ic_image_load, R.mipmap.ic_image_load);
    }

    /**
     * 常规图片加载
     *
     * @param context
     * @param url       图片链接
     * @param imageView 被加载视图
     */
    public static void loadImageNoImage(Activity context, String url, ImageView imageView) {
        Glide.with(context).load(url).asBitmap().into(imageView);
    }

    /**
     * 加载文件中的图片
     *
     * @param context
     * @param file      图片文件
     * @param imageView 被加载视图
     */
    public static void loadFileImage(Activity context, File file, ImageView imageView) {
        Glide.with(context).load(file).asBitmap().placeholder(R.mipmap.ic_image_load).error(R.mipmap.ic_image_load).animate(R.anim.fade_in).into(imageView);
    }


    /**
     * 加载res文件
     *
     * @param context
     * @param imageRes       本地的图片的资源ID
     * @param imageView      被加载视图
     * @param animRes        动画资源
     * @param placeHolderRes 加载中状态
     * @param errorRes       错误资源图
     */
    public static void loadResImage(Activity context, int imageRes, ImageView imageView, int animRes, int placeHolderRes, int errorRes) {
        Glide.with(context).load(imageRes).asBitmap().placeholder(placeHolderRes).error(errorRes).animate(animRes).into(imageView);
    }

    /**
     * 加载本地文件的图片
     *
     * @param context
     * @param imageFilePath  图片的地址
     * @param imageView      被加载视图
     * @param animRes        动画资源
     * @param placeHolderRes 加载过程中
     * @param errorRes       加载失败
     */
    public static void loadFileImage(Activity context, String imageFilePath, ImageView imageView, int animRes, int placeHolderRes, int errorRes) {
        File file = new File(imageFilePath);
        if (file.exists()) {
            Glide.with(context).load(file).asBitmap().placeholder(placeHolderRes).error(errorRes).animate(animRes).into(imageView);
        }
    }

    /**
     * 加载本地文件的图片
     *
     * @param context
     * @param imageFilePath  图片的地址
     * @param imageView      被加载视图
     */
    public static void loadFileImage(Activity context, String imageFilePath, ImageView imageView) {
        File file = new File(imageFilePath);
        if (file.exists()) {
            Glide.with(context).load(file).asBitmap().into(imageView);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param context   上下文
     * @param url       地址
     * @param imageView 控件
     */
    public static void loadRoundImage(final Activity context, String url, final ImageView imageView, int width, int height) {
        Glide.with(context).load(url).asBitmap().
                placeholder(R.mipmap.default_image).error(R.mipmap.ic_image_load).
                centerCrop().override(width, height).animate(R.anim.fade_in).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    /**
     * 加载圆形图片
     *
     * @param context   上下文
     * @param imageFile 图片文件
     * @param imageView 控件
     */
    public static void loadRoundImage(final Context context, String imageFile, final ImageView imageView) {
        File file = new File(imageFile);
        if (file.exists()) {
            Glide.with(context).load(imageFile).asBitmap().
                    placeholder(R.mipmap.ic_image_load).error(R.mipmap.ic_image_load).
                    centerCrop().override(200, 200).animate(R.anim.fade_in).into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    /**
     * 加载裁剪后的图片(Oss)
     *
     * @param context
     * @param url       链接
     * @param width     宽度
     * @param height    高度
     * @param imageView
     */
    public static void loadResizeImage(Context context, String url, int width, int height, ImageView imageView) {
        Glide.with(context).load(ImageUrlUtil.getReSizeImageUrl(url, width, height)).asBitmap().placeholder(R.mipmap.ic_image_load).error(R.mipmap.ic_image_load).animate(R.anim.fade_in).into(imageView);
    }

    /**
     * 加载裁剪后的图片（Oss）
     *
     * @param context
     * @param url       链接
     * @param width     宽度
     * @param imageView
     */
    public static void loadResizeImageW(Context context, String url, int width, ImageView imageView) {
        Glide.with(context).load(ImageUrlUtil.getReSizeImageUrlW(url, width)).asBitmap().placeholder(R.mipmap.ic_image_load).error(R.mipmap.ic_image_load).animate(R.mipmap.default_image).into(imageView);
    }

    /**
     * 加载裁剪后的图片(Oss)
     *
     * @param context
     * @param url       链接
     * @param height    高度
     * @param imageView
     */
    public static void loadResizeImageH(Context context, String url, int height, ImageView imageView) {
        Glide.with(context).load(ImageUrlUtil.getReSizeImageUrlH(url, height)).asBitmap().placeholder(R.mipmap.ic_image_load).error(R.mipmap.ic_image_load).animate(R.mipmap.default_image).into(imageView);
    }

    /**
     * 加载裁剪的头像图片（Oss）
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 控件
     */
    public static void loadHeadImage(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(ImageUrlUtil.getHeadReSizeImageUrl(url)).asBitmap().
                placeholder(R.mipmap.ic_image_load).error(R.mipmap.ic_image_load).
                centerCrop().animate(R.mipmap.ic_image_load).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }


}
