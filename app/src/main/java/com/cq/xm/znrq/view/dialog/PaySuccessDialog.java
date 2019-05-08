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
import android.widget.ImageView;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.util.SpannableStringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PaySuccessDialog extends DialogFragment {
//    @Bind(R.id.tv_total)
//    TextView tvTotal;
//    @Bind(R.id.tv_surplus)
//    TextView tvSurplus;
//    @Bind(R.id.tv_unit)
//    TextView tvUnit;
//    @Bind(R.id.tv_state)
//    TextView tvState;
//    @Bind(R.id.tv_break)
//    TextView tvBreak;
//    @Bind(R.id.iv_close)
//    ImageView ivClose;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialogThemeFullScr);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_success, container);
        ButterKnife.bind(this, view);
//        tvTotal.setText(new SpannableStringUtils(getActivity(), "累计气量: 51898").setColor(R.color.color_text_light, 0, 5).getSpannableString());
//        tvSurplus.setText(new SpannableStringUtils(getActivity(), "剩余气量: 34平方").setColor(R.color.color_text_light, 0, 5).getSpannableString());
//        tvUnit.setText(new SpannableStringUtils(getActivity(), "计费阶数: 20").setColor(R.color.color_text_light, 0, 5).getSpannableString());
//        tvState.setText(new SpannableStringUtils(getActivity(), "阀门状态: 开").setColor(R.color.color_text_light, 0, 5).getSpannableString());
//        tvBreak.setText(new SpannableStringUtils(getActivity(), "故障状态: 无故障").setColor(R.color.color_text_light, 0, 5).getSpannableString());
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

    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }
}
