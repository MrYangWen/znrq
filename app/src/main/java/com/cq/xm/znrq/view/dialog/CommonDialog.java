package com.cq.xm.znrq.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


import com.cq.xm.znrq.R;
import com.cq.xm.znrq.view.dialog.interf.ICommonDialogCallBack;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CommonDialog extends DialogFragment {

    @Bind(R.id.tv_qx)
    TextView mTvQx;
    @Bind(R.id.tv_sq)
    TextView mTvSq;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_content)
    TextView mTvContent;

    private String title;
    private String content;
    private String positiveText;
    private String negetiveText;
    private String tag;
    private ICommonDialogCallBack commonDialogCallBack;


    public static CommonDialog newInstance(@Nullable String title, String message, String positive, String negetive) {
        CommonDialog adf = new CommonDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        bundle.putString("positive", positive);
        bundle.putString("negetive", negetive);
        adf.setArguments(bundle);
        return adf;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            tag = getTag();
            title = bundle.getString("title");
            content = bundle.getString("message");
            positiveText = bundle.getString("positive");
            negetiveText = bundle.getString("negetive");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_common, container);
        ButterKnife.bind(this, view);
        if (!isEmpty(title)) {
            mTvTitle.setText(title);
        }
        if (!isEmpty(content)) {
            mTvContent.setText(content);
        }
        if (!isEmpty(positiveText)) {
            mTvSq.setText(positiveText);
        }
        if (!isEmpty(negetiveText)) {
            mTvQx.setText(negetiveText);
        }
        return view;
    }

    /**
     * 设置回掉
     *
     * @param callback
     */
    public void setICommonDialogCallback(ICommonDialogCallBack callback) {
        this.commonDialogCallBack = callback;
    }


    @OnClick({R.id.tv_qx, R.id.tv_sq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qx:
                if (commonDialogCallBack != null) {
                    commonDialogCallBack.onNegativeCase(tag);
                }
                dismiss();
                break;
            case R.id.tv_sq:
                if (commonDialogCallBack != null) {
                    commonDialogCallBack.onPositiveCase(tag);
                }
                dismiss();
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private boolean isEmpty(String s) {
        if (s != null && s.trim().length() > 0) {
            return false;
        }
        return true;
    }

}
