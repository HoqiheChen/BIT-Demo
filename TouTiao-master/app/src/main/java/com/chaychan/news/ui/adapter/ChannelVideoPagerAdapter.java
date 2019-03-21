package com.chaychan.news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chaychan.news.model.entity.Channel;
import com.chaychan.news.ui.fragment.NewsListFragment;
import com.chaychan.news.ui.fragment.TinyVideoListFragment;

import java.util.ArrayList;
import java.util.List;

public class ChannelVideoPagerAdapter extends FragmentStatePagerAdapter {

    private List<TinyVideoListFragment> mFragments;
    private List<Channel> mChannels;

    public ChannelVideoPagerAdapter(List<TinyVideoListFragment> fragmentList, List<Channel> channelList, FragmentManager fm) {
        super(fm);
        mFragments = fragmentList != null ? fragmentList : new ArrayList<>();
        mChannels = channelList != null ? channelList : new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).title;
    }

    @Override
    public int getItemPosition(Object object) {
        //        if (mChildCount > 0) {
        //            mChildCount--;
        return POSITION_NONE;
        //        }
        //        return super.getItemPosition(object);
    }
}
