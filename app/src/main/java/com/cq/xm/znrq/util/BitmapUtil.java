package com.cq.xm.znrq.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片工具类
 * <p/>
 * Created by luojiang on 2015/9/17.
 */
public class BitmapUtil {


//    // 图片编码
//    public static String convertImageToBase64(String fileName) throws FileNotFoundException, IOException {
//        InputStream in = null;
//        byte[] data = null;
//        in = new FileInputStream(fileName);
//        data = new byte[in.available()];
//        in.read(data);
//        in.close();
//        return encoder.encode(data);
//    }
//
//    // 图片解码
//    public static boolean generateImageFromBase64(String imageString, String output) throws IOException {
//        if (imageString == null)
//            return false;
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] bytes = decoder.decodeBuffer(imageString);
//        for (byte b : bytes) {
//            if (b < 0) {
//                b += 256;
//            }
//        }
//        OutputStream out = new FileOutputStream(output);
//        out.write(bytes);
//        out.flush();
//        out.close();
//
//        return true;
//    }

    /**
     * 根据指定的高度和宽度缩放图片
     *
     * @param filePath
     * @param reqWidth
     * @param reqHeight
     * @return
     * @author luojiang
     */
    public static Bitmap getSDCardBitmap(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap imageBitmap = null;
        BitmapFactory.decodeFile(filePath, options);
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        int realWidth = options.outWidth;
        int realheight = options.outHeight;
        if (reqWidth > reqHeight)
            options.inSampleSize = Math.round((float) realheight / (float) reqHeight);
        else
            options.inSampleSize = Math.round((float) realWidth / (float) reqWidth);

        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//		options.inPreferredConfig = Bitmap.Config.RGB_565; // 与ARGM_8888相比少小号2倍的内存,默认为ARGM_8888
        imageBitmap = BitmapFactory.decodeFile(filePath, options);
        return imageBitmap;
    }


    /**
     * 压缩 品质
     *
     * @param image
     * @return
     */
    public static Bitmap compressImageQuality(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 5;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    /**
     * 压缩尺寸
     *
     * @param srcPath
     * @return
     */
    public static Bitmap getimage(final String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        File file = new File(srcPath);

        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 640;//这里设置高度为640f
        float ww = 400;//这里设置宽度为400f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 1) {
            newOpts.inSampleSize = 1;
        } else {
            newOpts.inSampleSize = be * 2;//设置缩放比例
        }
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImageQuality(bitmap);//压缩好比例大小后再进行质量压缩
    }


    /**
     * 保存bitmap
     *
     * @param bitmap
     * @param savePath
     * @param saveFileName
     * @return
     */
    public static String saveBitmap(Bitmap bitmap, String savePath, String saveFileName) {
        // 图片文件名称
        if (TextUtils.isEmpty(saveFileName)) {
            saveFileName = (System.currentTimeMillis() + ".jpg");
        }
        File file = new File(savePath, saveFileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    /**
     * 复制图片到制定路径
     *
     * @param bitmapPath
     * @param savePath
     * @param saveFileName
     * @return
     */
    public static File saveBitmapByPath(String bitmapPath, String savePath, String saveFileName) {
        // 图片文件名称
        if (TextUtils.isEmpty(saveFileName)) {
            saveFileName = (System.currentTimeMillis() + ".jpg");
        }
        File file = new File(savePath, saveFileName);
        if (file.exists()) {
            file.delete();
        }
        File bitmapFile = new File(bitmapPath);
        if (bitmapFile.exists()) {
            try {
                FileInputStream in = new FileInputStream(bitmapFile);
                Bitmap tbitmap = BitmapFactory.decodeStream(in);
                FileOutputStream out = new FileOutputStream(file);
                tbitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
                tbitmap.recycle();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    //view 转bitmap
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

}
