package com.example.yashwanth_thotabe_happy.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

//Each time we get a quote we create a Fragment

public class QuoteViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public QuoteViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {

        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

}
