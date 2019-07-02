package com.cq.xm.znrq.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cq.xm.znrq.R;


/**
 * @title title工具类
 * @date 2016-12-14
 * @email JackMar
 */
public class TemplateTitle extends RelativeLayout {

    private String titleText = "";
    private boolean canBack;
    private String backText;
    private String moreText;
    private int textcolor;
    private int moreImg;
    private int backImg;
    private TextView tvMore;
    private ImageView moreImgView;
    private TextView tvTitle;
    private ImageView backImage;
    private TextView tvBack;


    public TemplateTitle(Context context) {
        super(context);
        init(context, null);
    }

    public TemplateTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TemplateTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TemplateTitle, 0, 0);
        try {
            titleText = ta.getString(R.styleable.TemplateTitle_titleText);
            canBack = ta.getBoolean(R.styleable.TemplateTitle_canBack, false);
            backText = ta.getString(R.styleable.TemplateTitle_backText);
            moreImg = ta.getResourceId(R.styleable.TemplateTitle_moreImg, 0);
            backImg = ta.getResourceId(R.styleable.TemplateTitle_backImage, 0);
            moreText = ta.getString(R.styleable.TemplateTitle_moreText);
            textcolor = ta.getResourceId(R.styleable.TemplateTitle_textcolor, 0);
            setUpView();
        } finally {
            ta.recycle();
        }
    }

    private void setUpView() {
        tvTitle = (TextView) findViewById(R.id.title);
        backImage = (ImageView) findViewById(R.id.img_back);
//        if (textcolor != 0) {
//            tvTitle.setTextColor(textcolor);
//        }
        if (!TextUtils.isEmpty(titleText) && tvTitle != null) {
            tvTitle.setText(titleText);
        }
        LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
        backBtn.setVisibility(canBack ? VISIBLE : INVISIBLE);
        if (canBack) {
            tvBack = (TextView) findViewById(R.id.txt_back);
            tvBack.setText(backText);
            backBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        }
        if (moreImg != 0) {
            moreImgView = (ImageView) findViewById(R.id.img_more);
            moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
        }
        if (backImg != 0) {
            backImage = (ImageView) findViewById(R.id.img_back);
            backImage.setImageResource(backImg);
        }

//        if (textcolor != 0) {
//            tvTitle.setTextColor(textcolor);
//        } else {
//            tvTitle.setTextColor(getResources().getColor(R.color.color_white));
//        }
        tvMore = (TextView) findViewById(R.id.txt_more);
        tvMore.setText(moreText);
        moreImgView = (ImageView) findViewById(R.id.img_more);
    }


    /**
     * 标题控件
     *
     * @param titleText 设置标题文案
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
        TextView tvTitle = (TextView) findViewById(R.id.title);
//        if (textcolor == 0) {
//            tvTitle.setTextColor(getResources().getColor(R.color.color_white));
//        } else {
//            tvTitle.setTextColor(textcolor);
//        }
        tvTitle.setText(titleText);
    }


    public TextView getTitle() {
        if (tvTitle != null) {
            return tvTitle;
        }
        return null;
    }

    /**
     * 标题更多按钮
     *
     * @param img 设置更多按钮
     */
    public void setMoreImg(int img) {
        moreImg = img;
        if (moreImgView.getVisibility() == View.GONE) {
            moreImgView.setVisibility(View.VISIBLE);
            return;
        }
        moreImgView.setImageResource(moreImg);
    }

    public void setGone() {
        moreImgView.setVisibility(View.GONE);
    }

    public ImageView getMoreImgView() {
        return moreImgView;
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreImgAction(OnClickListener listener) {
        moreImgView.setOnClickListener(listener);
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreTextAction(OnClickListener listener) {
        tvMore.setOnClickListener(listener);
    }


    /**
     * 设置更多文字内容
     *
     * @param text 更多文本
     */
    public void setMoreTextContext(String text) {
        tvMore.setText(text);
    }

    /**
     * 设置返回图标
     *
     * @param res
     */
    public void setBackImg(int res) {
        backImage.setImageResource(res);
    }


    /**
     * 设置返回按钮事件
     *
     * @param listener 事件监听
     */
    public void setBackListener(OnClickListener listener) {
        if (canBack) {
            LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
            backBtn.setOnClickListener(listener);
        }
    }


}
