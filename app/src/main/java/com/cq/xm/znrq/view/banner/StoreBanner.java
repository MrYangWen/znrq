package com.cq.xm.znrq.view.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.cq.xm.znrq.util.GlideImageLoadUtil;
import com.cq.xm.znrq.util.ViewUtil;


public class StoreBanner implements Holder<Integer> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        if (mImageView == null) {
            mImageView = new ImageView(context);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewUtil.setViewSize(mImageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
//        GlideImageLoadUtil.loadImage(context, data, mImageView);
        mImageView.setImageResource(data);
    }
}
