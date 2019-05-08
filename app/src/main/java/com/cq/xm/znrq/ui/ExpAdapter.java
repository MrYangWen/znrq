package com.cq.xm.znrq.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cq.xm.znrq.R;
import com.cq.xm.znrq.bean.Entity;

import java.util.List;

/**
 * Created by JackMar on 2017/6/19.
 * 邮箱：1261404794@qq.com
 */

public class ExpAdapter extends BaseExpandableListAdapter {
    private List<Entity> entities;
    private Context context;
    private ExpandableListView listView;

    public ExpAdapter(List<Entity> entities, Context context, ExpandableListView listView) {
        this.entities = entities;
        this.context = context;
        this.listView = listView;
    }

    @Override
    public int getGroupCount() {
        return entities.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View groupView = LayoutInflater.from(context).inflate(R.layout.cell_group, null);
        TextView textView = (TextView) groupView.findViewById(R.id.tv_text);
        ImageView imageView = (ImageView) groupView.findViewById(R.id.iv_arrow);
        if (listView.isGroupExpanded(i)) {
            imageView.setImageResource(R.mipmap.bzzx_sjt);
        } else {
            imageView.setImageResource(R.mipmap.whzx_xx);
        }
        textView.setText((i + 1) + ":\t" + entities.get(i).getTitle());
        return groupView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View childView = LayoutInflater.from(context).inflate(R.layout.cell_child, null);
        TextView textView = (TextView) childView.findViewById(R.id.tv_answer);
        TextView tv_ans = (TextView) childView.findViewById(R.id.tv_ans);
//        tv_ans.setText((i + 1) + ":");
        textView.setText(entities.get(i).getChild().getContent());
        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
