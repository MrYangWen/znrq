package com.cq.xm.znrq.base;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;


/***
 * 重写 FragmentPagerAdapter
 */
public class FGPagerAdapter extends FragmentPagerAdapter {

    /**
     * The m fragment list.
     */
    private ArrayList<Fragment> mFragmentList = null;

    /**
     * Instantiates a new ab fragment pager adapter.
     *
     * @param mFragmentManager the m fragment manager
     * @param fragmentList     the fragment list
     */
    public FGPagerAdapter(FragmentManager mFragmentManager, ArrayList<Fragment> fragmentList) {
        super(mFragmentManager);
        mFragmentList = fragmentList;
    }

    /**
     * 描述：获取数量.
     *
     * @return the count
     * @see PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 描述：获取索引位置的Fragment.
     *
     * @param position the position
     * @return the item
     * @see FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position < mFragmentList.size()) {
            fragment = mFragmentList.get(position);
        } else {
            fragment = mFragmentList.get(0);
        }
        return fragment;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment f = (Fragment) super.instantiateItem(container, position);
        return f;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
