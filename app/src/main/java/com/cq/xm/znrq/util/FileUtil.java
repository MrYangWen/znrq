package com.cq.xm.znrq.util;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;


import com.cq.xm.znrq.XApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static final String parentPath = "luckydog/";
    /**
     * 压缩帖子目录
     */
    public static final String IMG_DIR = File.separator + parentPath + "image";


    /**
     * 描述：获取src中的图片资源.
     *
     * @param src 图片的src路径，如（“image/arrow.png”）
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFormSrc(String src) {
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream(FileUtil.class
                    .getResourceAsStream(src));
        } catch (Exception e) {

        }
        return bit;
    }

    /**
     * 获取项目缓存文件
     *
     * @return
     */
    public static File getCacheDownloadDir() {
        File file = new File(getDir().getAbsolutePath() + "/download");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取项目文件夹
     *
     * @return
     */
    public static File getDir() {
        Context context = XApplication.getInstance();
        String packname = context.getPackageName();
        String name = packname.substring(packname.lastIndexOf(".") + 1, packname.length());
        File dir = null;
        if ((!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))) {
            dir = context.getCacheDir();
        } else {
            dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/yaya/" + name);
        }
        dir.mkdirs();
        return dir;
    }

    /**
     * 新图片生成
     *
     * @param context
     * @return
     */
    public static File createNewImageFile(Context context) {
        File mediaStorageDir = null;
        if (isCanUseSD()) {// 存在sd卡
            mediaStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "yaya");
        } else {
            mediaStorageDir = new File(context.getCacheDir().getAbsolutePath(), "yaya");
        }
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(
                mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg"
        );

        return mediaFile;
    }


    /**
     * 多图选择后图片压缩需要压缩而生成的图片地址
     *
     * @param context
     * @return
     */
    public static File createNewImageFile(Context context, String imageName, String hz) {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "yaya");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        File mediaFile = new File(
                mediaStorageDir.getPath() + File.separator + imageName + hz
        );

        return mediaFile;
    }


    /**
     * 转换
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitamp(Drawable drawable) {
        Bitmap bitmap = null;
        try {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bitmap = bd.getBitmap();
        } catch (Exception e) {

        }
        return bitmap;
    }

    /**
     * 删除文件夹
     *
     * @param dirf
     */
    public static void deleteDir(File dirf) {
        if (dirf.isDirectory()) {
            File[] childs = dirf.listFiles();
            for (int i = 0; i < childs.length; i++) {
                deleteDir(childs[i]);
            }
        }
        dirf.delete();
    }


    /**
     * uri装换文件
     *
     * @param context
     * @param uri
     * @return
     */
    public static File uriToFile(Activity context, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = context.managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        File file = new File(img_path);
        return file;
    }

    /**
     * 写入文件
     *
     * @param in
     * @param file
     */
    public static void write(InputStream in, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while (in.read(buffer) > -1) {
                out.write(buffer);
            }
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void write(byte[] buffer, File file) {
        if (file.exists()) file.delete();

        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            out.write(buffer);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 写入文件
     *
     * @param in
     * @param file
     */
    public static void write(String in, File file, boolean append) {
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file, append);
            fw.write(in);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读文件
     *
     * @param file
     * @return
     */
    public static String read(File file) {
        if (!file.exists()) {
            return "";
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            StringBuffer buffer = new StringBuffer();
            String s;
            while ((s = br.readLine()) != null) {
                buffer.append(s);
            }
            return buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 计算文件的大小
     *
     * @param file
     * @return
     */
    public static long computeFileSize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int filesNum = files.length;
            for (int i = 0; i < filesNum; i++) {
                File childFile = files[i];
                if (childFile.isDirectory()) {
                    size += computeFileSize(childFile);
                } else {
                    size += childFile.length();
                }
            }
        } else {
            size = file.length();
        }
        return size;
    }


    /**
     * @param context 句柄
     * @return
     */
    public static String getAppSdkDbPath(Context context) {
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//            //已经挂载
//            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gutou/");
//            return file.getAbsolutePath();
//        } else {
//            File cacheDir = context.getCacheDir();
//            return cacheDir.getAbsolutePath();
//        }
        File cacheDir = context.getCacheDir();
        return cacheDir.getAbsolutePath();
    }

    /**
     * 描述：SD卡是否能用.
     *
     * @return true 可用,false不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取到发帖压缩目录
     *
     * @param context
     * @return
     */
    public static String getImgDir(Context context) {
        String path = null;
        if (isCanUseSD()) {// 存在sd卡
            path = Environment.getExternalStorageDirectory() + IMG_DIR;
        } else {
            path = context.getCacheDir().getAbsolutePath() + IMG_DIR;
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return path;
    }

    public static int getMB(String path) {
        int mb = 0;
        try {
            File file = new File(path);
            long size = getFileSize(file);
            mb = (int) (size / 1048576);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mb;
    }

    public static int getKB(String path) {
        int mb = 0;
        try {
            File file = new File(path);
            long size = getFileSize(file);
            mb = (int) (size / 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mb;
    }

    public static long getFileSize(File f) throws Exception {
        long size = 0;
        if (f.isDirectory()) {
            File flist[] = f.listFiles();
            for (int i = 0; i < flist.length; i++) {
                File file = flist[i];
                if (file.isDirectory()) {
                    size = size + getFileSize(file);
                } else {
                    size = size + file.length();
                }
            }
        } else {
            size = f.length();
        }
        return size;
    }


    /**
     * 获取根路径 返回
     *
     * @return
     */
    public static String getParenPath(Context context) {
        String path = "";
        if (isCanUseSD()) {// 存在sd卡
            path = Environment.getExternalStorageDirectory() + File.separator + parentPath;
        } else {
            path = context.getCacheDir().getAbsolutePath() + File.separator + parentPath;
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * 获取文件大小
     *
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.0");
        String fileSizeString = "";
        if (fileS == 0) {
            fileSizeString = "";
        } else if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + " B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + " K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + " M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + " G";
        }
        return fileSizeString;
    }

    // 清楚缓存
    public static boolean clearCache(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
        return true;
    }

    public static void delFile(String path) {
        if (StrUtil.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                delFile(childFiles[i].getPath());
            }
            file.delete();
        }
    }
}
