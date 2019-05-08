package com.cq.xm.znrq.view.imageselect;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.cq.xm.znrq.R;
import com.cq.xm.znrq.util.ScreenUtil;
import com.cq.xm.znrq.util.ToastUtil;
import com.cq.xm.znrq.util.ViewUtil;
import com.cq.xm.znrq.view.imagepicker.ImagePickerUtils;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JackMar on 2017/3/16.
 * 邮箱：1261404794@qq.com
 */

public class SelectItem extends RelativeLayout {
    @Bind(R.id.civ_image)
    ImageView mIvImage;
    private Context context;
    private View view;
    private OnImageSeletListener listener;
    private int selectedSize;
    private int count;
    private int canSelect = 9;
    private List<ImageItem> imageItems;

    public SelectItem(Context context) {
        super(context);
        init(context);
    }

    public SelectItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelectItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.cell_image_item, this, true);
        ButterKnife.bind(this);
        int w = (ScreenUtil.defaultCenter().getWidth() - ViewUtil.dip2px(context, 40)) / 4;
        ViewUtil.setViewSize(mIvImage, w, w);
        mIvImage.setImageResource(R.mipmap.xuxiankuang);
        mIvImage.setScaleType(ImageView.ScaleType.FIT_XY);
        mIvImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count - selectedSize <= 0) {
                    ToastUtil.getInstance().showToast(context, "图片选择已达上限");
                    return;
                }
                ImagePickerUtils.setSelectCount(canSelect);
                Intent intent = new Intent(context, ImageGridActivity.class);
//                XActivityManager.getInstance().getActivityByClass(WriteNoticeAct.class).startActivityForResult(intent, 100);

            }
        });
    }

    public void setCount(int count, int selectedSize, List<ImageItem> imageItems) {
        this.selectedSize = selectedSize;
        this.count = count;
        this.imageItems = imageItems;
        canSelect = count - selectedSize;
    }

    public void setVisible(boolean visible) {
        if (visible) {
            mIvImage.setVisibility(VISIBLE);
        } else {
            mIvImage.setVisibility(GONE);
        }
    }


    public void setOnImageSeletListener(OnImageSeletListener listener) {
        this.listener = listener;
    }

    /**
     * 添加图片按钮点击事件
     */
    public interface OnImageSeletListener {
        void onImageSelect(List<ImageItem> photoInfos);
    }
}
