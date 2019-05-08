package com.cq.xm.znrq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.util.ViewUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 错误视图
 * Created by JackMar on 2017/7/4.
 * 邮箱：1261404794@qq.com
 */

public class ErrorView extends ViewBase {
    @Bind(R.id.iv_image)
    ImageView mIvImage;
    @Bind(R.id.tv_text)
    TextView mTvText;
    private Context context;
    private View view;

    public ErrorView(Context context) {
        super(context);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.error_view, this, true);
        ViewUtil.setViewSize(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ButterKnife.bind(this);
    }

    /**
     * 设置错误图片
     *
     * @param res
     */
    public void setImage(int res) {
        mIvImage.setImageResource(res);
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setmTvText(String text) {
        mTvText.setText(text);
    }
}
