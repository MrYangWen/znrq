package com.cq.xm.znrq.manager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.cq.xm.znrq.XApplication;
import com.cq.xm.znrq.http.exception.retrofitexception.ApiException;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;

import java.util.HashMap;


public class OssManager {

    private static OssManager single = null;
    private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static final String accessKeyId = "UWEceUXZDWig3WDt";
    private static final String accessKeySecret = "5okZB2wEzHIks0i1kOriAoXvkUIx3O";
    public static final String testBucket = "kdmeitu";//服务器文件地址
    public static final String test_call_back = "https://www.leiruiqi.com/osscallback/PublishNotice";//服务器回掉地址
    private OSS oss;

    public synchronized static OssManager getInstance() {
        if (single == null) {
            single = new OssManager();
        }
        return single;
    }

    public OssManager() {
        if (oss == null) {
            OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
            ClientConfiguration conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
            conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
            conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
            OSSLog.enableLog();
            oss = new OSSClient(XApplication.getInstance(), endpoint, credentialProvider, conf);
        }
    }

    /**
     * 上传图片
     *
     * @param imgPath     图片链接
     * @param imgName     图片名字
     * @param bodyJson
     * @param callBackUrl 自己服务器的回掉地址（后台提供）
     */
    public void uploadImage(final String imgPath, final String imgName, final String bodyJson, final String callBackUrl, final ProgressSubscriber<JSONObject> subscriber) {
        subscriber.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //压缩图片
//                String newPath = imgPath;
                // 构造上传请求
                PutObjectRequest mPutObjectRequest = new PutObjectRequest(testBucket, imgName, imgPath);
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("callbackUrl", callBackUrl);
                hashMap.put("callbackBody", String.format("%s", bodyJson));
                mPutObjectRequest.setCallbackParam(hashMap);//设置回掉地址

                // 异步上传时可以设置进度回调
                mPutObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                    @Override
                    public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                        Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                        Message message = new Message();
                        message.what = ENUM_UPLOAD.PROGRESS.ordinal();
                        UploadModel model = new UploadModel();
                        model.subscriber = subscriber;
                        model.currentSize = currentSize;
                        model.totalSize = totalSize;
                        message.obj = model;
                        handler.sendMessage(message);
                    }
                });

                oss.asyncPutObject(mPutObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                    @Override
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {

                        Log.e("---------------", JSON.toJSONString(request));
                        Log.e("---------------", JSON.toJSONString(result));

                        Log.e("PutObject", "UploadSuccess");
                        Log.e("ETag", result.getETag());
                        Log.e("RequestId", result.getRequestId());
                        Log.e("Body", result.getServerCallbackReturnBody());
                        Message message = new Message();
                        message.what = ENUM_UPLOAD.SUCCESS.ordinal();
                        UploadModel model = new UploadModel();
                        model.subscriber = subscriber;
                        model.result = result.getServerCallbackReturnBody();
                        message.obj = model;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                        Message message = new Message();
                        message.what = ENUM_UPLOAD.FAIL.ordinal();
                        UploadModel model = new UploadModel();
                        model.subscriber = subscriber;
                        message.obj = model;
                        // 请求异常
                        if (clientExcepion != null) {
                            // 本地异常如网络异常等
                            clientExcepion.printStackTrace();
                            model.result = "上传失败 客户端异常" + clientExcepion.toString();
                        }
                        if (serviceException != null) {
                            model.result = "上传失败 服务器异常" + serviceException.getRawMessage();
//                            // 服务异常
                            Log.e("ErrorCode", serviceException.getErrorCode());
                            Log.e("RequestId", serviceException.getRequestId());
                            Log.e("HostId", serviceException.getHostId());
                            Log.e("RawMessage", serviceException.getRawMessage());
                        }
                        handler.sendMessage(message);
                    }
                });
            }
        }).start();

    }

    public Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            UploadModel model = (UploadModel) msg.obj;
            JSONObject jsonObject = null;
            if (msg.what == ENUM_UPLOAD.SUCCESS.ordinal()) { //上传成功
                try {
                    jsonObject = JSON.parseObject(model.result);
                    if (model.subscriber != null) {
                        model.subscriber.onNext(jsonObject);
                    }
                } catch (Exception e) {
                    if (model.subscriber != null)
                        model.subscriber.onError(new ApiException(e.getMessage()));
                }
            }
            if (msg.what == ENUM_UPLOAD.FAIL.ordinal()) { //上传失败
                if (model.subscriber != null) {
                    if (model.subscriber != null)
                        model.subscriber.onError(new ApiException("上传失败"));
                }
            }
            if (msg.what == ENUM_UPLOAD.PROGRESS.ordinal()) { //上传中
                float bili = Float.valueOf(model.currentSize + "") / Float.valueOf(model.totalSize + "");
                int bfb = (int) (bili * 100);
//                if (model.callBack != null)
//                    model.callBack.uploadProgress("", null, model.currentSize, model.totalSize, bfb);
            }
        }
    };

    private enum ENUM_UPLOAD {
        SUCCESS,
        FAIL,
        PROGRESS
    }

    class UploadModel {
        public String result;
        public long currentSize;
        public long totalSize;
        public ProgressSubscriber<JSONObject> subscriber;
    }
}
