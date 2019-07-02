package com.cq.xm.znrq.view.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.ui.activity.UrlSettingAct;
import com.cq.xm.znrq.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UrlSettingDialog extends DialogFragment {


    @Bind(R.id.tv_content)
    EditText mTvContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialogThemeFullScr);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_urlsetting, container);
        ButterKnife.bind(this, view);

        return view;
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

    @OnClick({R.id.tv_qx, R.id.tv_sq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qx:
                dismiss();
                break;
            case R.id.tv_sq:
                String psd = mTvContent.getText().toString();
                if (!psd.equals("admin123456")) {
                    ToastUtil.getInstance().showToast(getContext(), "不正确");
                    return;
                }
                startActivity(new Intent(getActivity(), UrlSettingAct.class));
                dismiss();
                break;
        }
    }
}
