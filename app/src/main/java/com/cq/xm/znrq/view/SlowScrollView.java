package com.cq.xm.znrq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by JackMar on 2017/6/7.
 * 邮箱：1261404794@qq.com
 */

public class SlowScrollView extends ScrollView {
    public SlowScrollView(Context context) {
        super(context);
    }

    public SlowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlowScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY / 2, consumed);
    }
}
