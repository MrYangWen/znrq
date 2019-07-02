package com.cq.xm.znrq.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.base.BaseActivity;
import com.cq.xm.znrq.bean.AdminRepairEntity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httphelper.HttpHelperUser;
import com.cq.xm.znrq.http.subscriber.IOnNextListener;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.manager.UserManager;
import com.cq.xm.znrq.util.GlideImageLoadUtil;
import com.cq.xm.znrq.view.imagepicker.ImagePickerUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dapeng
 * @title 安全检修
 * @data 2018/1/31
 * @email 919142784@qq.com
 */
public class adminRepairAct extends BaseActivity {

    @Bind(R.id.img_pz)
    ImageView mImgPz;
    @Bind(R.id.et_txm)
    EditText mEtTxm;
    @Bind(R.id.img_txm)
    ImageView mImgTxm;
    @Bind(R.id.rb_yes)
    RadioButton mRbYes;
    @Bind(R.id.rb_no)
    RadioButton mRbNo;
    @Bind(R.id.rg_actor)
    RadioGroup mRgActor;
    @Bind(R.id.et_zd)
    EditText mEtZd;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.et_bz)
    EditText mEtBz;
    @Bind(R.id.tv_updata)
    TextView mTvUpdata;

    private String url;

    private String type = "00";  // 00：正常 /01：异常


    @Override
    protected int getLayoutId() {
        return R.layout.act_admin_reoair;
    }

    @Override
    protected void initView() {
        UserEntity entity = UserManager.getInstance().getUser();
        if (entity != null) {
            mTvName.setText(entity.getAccount().getUserName());
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String time = formatter.format(curDate);
        mTvTime.setText(time);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 选择图片
     */
    public void chooseImage(int count) {
        ImagePickerUtils.setSelectCount(count);
        Intent intent = new Intent(context, ImageGridActivity.class);
        startActivityForResult(intent, 1010);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1010:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                    ArrayList<ImageItem> imageItems = new ArrayList<>();
                    imageItems.addAll((Collection<? extends ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                    url = imageItems.get(0).path;
                    GlideImageLoadUtil.loadImage(context, url, mImgPz);

                }
                break;
            case 1002:
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (resultCode == RESULT_OK) {
                        String result = bundle.getString("code");
                        mEtTxm.setText(result);
                    }
                    break;
                }
        }
    }

    @OnClick({R.id.img_pz, R.id.img_txm, R.id.rb_yes, R.id.rb_no, R.id.tv_updata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_pz:
                chooseImage(1);
                break;
            case R.id.img_txm:
                Intent intent = new Intent(context, SimpleCaptureActivity.class);
                startActivityForResult(intent, 1002);
                break;
            case R.id.rb_yes:
                type = "00";
                break;
            case R.id.rb_no:
                type = "01";
                break;
            case R.id.tv_updata:
                if (TextUtils.isEmpty(url)) {
                    showToast("请拍照");
                    return;
                }
                String txm = mEtTxm.getText().toString();
                if (TextUtils.isEmpty(txm)) {
                    showToast("请输入条形码");
                    return;
                }
                String zd = mEtZd.getText().toString();
                if (TextUtils.isEmpty(zd)) {
                    showToast("请输入止度");
                    return;
                }
                String bz = mEtBz.getText().toString();
                HttpHelperUser.getInstance().checkSave(UserManager.getInstance().getUserId(), txm, zd, mTvName.getText().toString(), mTvTime.getText().toString(), type, bz, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<AdminRepairEntity>() {
                    @Override
                    public void onNext(AdminRepairEntity adminRepairEntity) {
                        HttpHelperUser.getInstance().upload(adminRepairEntity.getRoutingInspection().getId() + "", url, new ProgressSubscriber<BaseResult>(context, new IOnNextListener<BaseResult>() {
                            @Override
                            public void onNext(BaseResult result) {
                                showToast(result.getMessage());
                            }
                        }));
                    }
                }));
                break;
        }
    }
}
