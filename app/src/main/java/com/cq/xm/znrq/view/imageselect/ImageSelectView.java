package com.cq.xm.znrq.view.imageselect;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.util.GlideImageLoadUtil;
import com.cq.xm.znrq.util.ScreenUtil;
import com.cq.xm.znrq.util.ViewUtil;
import com.cq.xm.znrq.view.hrecyclerview.HorizontalRecyclerview;
import com.lzy.imagepicker.bean.ImageItem;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 图片选择控件
 * Created by JackMar on 2017/3/16.
 * 邮箱：1261404794@qq.com
 */

public class ImageSelectView extends RelativeLayout {
    @Bind(R.id.lv_image)
    HorizontalRecyclerview mLvImage;
    private Context context;
    private View view;
    //选择图片的张数
    private int IMAGE_COUNT = 1;
    //选择的图片的链接
    private List<ImageItem> imagePath = new ArrayList<>();
    //设置图片呈现为横向
    private int HORIZENTAL = 1;
    //设置图片呈现为网格
    private int GRID = 2;
    private HeaderAndFooterWrapper wrapper;
    private CommonAdapter<ImageItem> mAdapter;
    private SelectItem selectItem;
    private OnImageClick listener;

    public ImageSelectView(Context context) {
        super(context);
        init(context);
    }

    public ImageSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.layout_image_select_view, this, true);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLvImage.setLayoutManager(manager);
        mAdapter = new CommonAdapter<ImageItem>(context, R.layout.cell_image_item, imagePath) {
            @Override
            protected void convert(ViewHolder holder, ImageItem photoInfo, int position) {
                ImageView imageView = holder.getView(R.id.civ_image);
                int w = (ScreenUtil.defaultCenter().getWidth() - ViewUtil.dip2px(context, 40)) / 4;
                ViewUtil.setViewSize(imageView, w, w);
                GlideImageLoadUtil.loadFileImage((Activity) context, photoInfo.path, imageView,
                        R.anim.fade_in, R.mipmap.ic_image_load, R.mipmap.ic_image_load);
            }
        };
        selectItem = new SelectItem(context);
        selectItem.setCount(9, imagePath.size(), imagePath);
        wrapper = new HeaderAndFooterWrapper(mAdapter);
        wrapper.addFootView(selectItem);
        mLvImage.setAdapter(wrapper);
        selectItem.setOnImageSeletListener(new SelectItem.OnImageSeletListener() {
            @Override
            public void onImageSelect(List<ImageItem> photoInfos) {
                if (imagePath.size() + photoInfos.size() < 9) {
                    imagePath.addAll(photoInfos);
                    wrapper.notifyDataSetChanged();
                    selectItem.setCount(9, imagePath.size(), imagePath);
                } else if (imagePath.size() + photoInfos.size() == 9) {
                    imagePath.addAll(photoInfos);
                    wrapper.notifyDataSetChanged();
                    selectItem.setCount(9, imagePath.size(), imagePath);
                    selectItem.setVisible(false);
                }
            }
        });

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (listener != null) {
                    if (imagePath != null) {
                        listener.onClick(position, imagePath);
                    }
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public void setImage(List<ImageItem> image) {
        if (image != null) {
            imagePath.clear();
            imagePath.addAll(image);
            wrapper.notifyDataSetChanged();
            if (image.size() < 9) {
                selectItem.setVisible(true);
                selectItem.setCount(9, imagePath.size(), imagePath);
            } else {
                selectItem.setVisible(false);
            }
        } else {
            imagePath.clear();
            wrapper.notifyDataSetChanged();
            selectItem.setVisible(true);
            selectItem.setCount(9, imagePath.size(), imagePath);
        }
    }

    public List<ImageItem> getPath() {
        return imagePath;
    }

    public void setOnImageClick(OnImageClick imageClick) {
        this.listener = imageClick;
    }

    public interface OnImageClick {
        void onClick(int position, List<ImageItem> photoInfos);
    }
}
