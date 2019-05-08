package com.cq.xm.znrq.view.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.cq.xm.znrq.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class TabMenuView extends RelativeLayout implements View.OnClickListener {

    int tab_last_page = 0;

    ArrayList<ImageView> tab_imgs;
    ArrayList<TextView> tab_txts;
    ImageView tab_last_img;
    TextView tab_last_txt;
    @Bind(R.id.img_tab_1)
    ImageView mImgTab1;
    @Bind(R.id.txt_tab_1)
    TextView mTxtTab1;
    @Bind(R.id.layout_tab_1)
    LinearLayout mLayoutTab1;
    @Bind(R.id.img_tab_2)
    ImageView mImgTab2;
    @Bind(R.id.txt_tab_2)
    TextView mTxtTab2;
    @Bind(R.id.layout_tab_2)
    LinearLayout mLayoutTab2;
    @Bind(R.id.img_tab_3)
    ImageView mImgTab3;
    @Bind(R.id.txt_tab_3)
    TextView mTxtTab3;
    @Bind(R.id.layout_tab_3)
    RelativeLayout mLayoutTab3;
    @Bind(R.id.img_tab_4)
    ImageView mImgTab4;
    @Bind(R.id.txt_tab_4)
    TextView mTxtTab4;
    @Bind(R.id.layout_tab_4)
    LinearLayout mLayoutTab4;
    @Bind(R.id.main_tab_group)
    LinearLayout mMainTabGroup;

    /**
     * 接口回掉
     */
    private onPageSeletedDelegate delegate;

    public TabMenuView(Context context) {
        super(context);
        this.initView(context);
    }

    public TabMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public TabMenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initView(context);

    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_tab_menu, this, true);
        ButterKnife.bind(view);
        mLayoutTab1.setOnClickListener(this);
        mLayoutTab2.setOnClickListener(this);
        mLayoutTab3.setOnClickListener(this);
        mLayoutTab4.setOnClickListener(this);


        tab_imgs = new ArrayList<>();
        tab_txts = new ArrayList<>();


        tab_txts.add(mTxtTab1);
        tab_txts.add(mTxtTab2);
        tab_txts.add(mTxtTab3);
        tab_txts.add(mTxtTab4);

        tab_imgs.add(mImgTab1);
        tab_imgs.add(mImgTab2);
        tab_imgs.add(mImgTab3);
        tab_imgs.add(mImgTab4);

        tab_last_img = tab_imgs.get(0);
        tab_last_txt = tab_txts.get(0);


        setItemSelected(true, tab_txts.get(0), tab_imgs.get(0));
    }

    public void setPage(int page) {
        if (page == tab_last_page) return;

        //设置当前页面的按钮状态
        setItemSelected(true, tab_txts.get(page), tab_imgs.get(page));

        //设置上一次为默认状态
        setItemSelected(false, tab_last_txt, tab_last_img);

        //重新设置值
        tab_last_img = tab_imgs.get(page);
        tab_last_txt = tab_txts.get(page);

        tab_last_page = page;
    }


    @Override
    public void onClick(View view) {
        int page = 0;
        if (view.getId() == R.id.layout_tab_1) {
            page = 0;
        }
        if (view.getId() == R.id.layout_tab_2) {
            page = 1;
        }
        if (view.getId() == R.id.layout_tab_3) {
            page = 2;
        }
        if (view.getId() == R.id.layout_tab_4) {
            page = 3;
        }
        if (delegate != null) delegate.onPageSelected(page);
    }


    private void setItemSelected(boolean isSelected, TextView txt, ImageView img) {
        if (!isSelected) {
            //设置上一次为默认状态
            txt.setTextColor(getResources().getColor(R.color.tab_text_normal_color));
            img.setSelected(isSelected);
        } else {
            //设置上一次为默认状态
            txt.setTextColor(getResources().getColor(R.color.tab_text_selected_color));
            img.setSelected(isSelected);
        }
    }

    /**
     * 接口回掉
     */
    public interface onPageSeletedDelegate {
        public void onPageSelected(int page);
    }

    public void setDelegate(onPageSeletedDelegate delegate) {
        this.delegate = delegate;
    }
}
