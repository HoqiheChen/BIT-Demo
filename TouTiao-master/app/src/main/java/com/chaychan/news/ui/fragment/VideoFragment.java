package com.chaychan.news.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chaychan.news.R;
import com.chaychan.news.constants.Constant;
import com.chaychan.news.model.entity.Channel;
import com.chaychan.news.ui.adapter.ChannelPagerAdapter;
import com.chaychan.news.base.BaseFragment;
import com.chaychan.news.base.BasePresenter;
import com.chaychan.news.ui.adapter.ChannelVideoPagerAdapter;
import com.chaychan.news.utils.UIUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.jzvd.Jzvd;
import me.weyye.library.colortrackview.ColorTrackTabLayout;

/**
 * @author ChayChan
 * @description: 视频fragment
 * @date 2017/6/12  21:47
 */

public class VideoFragment extends BaseFragment {


    @Bind(R.id.vp_content)
    ViewPager mVpContent;

    private List<Channel> mChannelList = new ArrayList<>();
    private List<NewsListFragment> mFragmentList = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initData() {
        initChannelData();
        initChannelFragments();
    }

    private void initChannelData() {
        String[] channels = getResources().getStringArray(R.array.channel_video);
        String[] channelCodes = getResources().getStringArray(R.array.channel_code_video);
        for (int i = 0; i < channelCodes.length; i++) {
            String title = channels[i];
            String code = channelCodes[i];
            mChannelList.add(new Channel(title, code));
        }
    }

    private void initChannelFragments() {
        for (Channel channel : mChannelList) {
            NewsListFragment newsListFragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CHANNEL_CODE, channel.channelCode);
            bundle.putBoolean(Constant.IS_VIDEO_LIST, true);//是否是视频列表页面,]true
            bundle.putBoolean(Constant.IS_TINY_VIDEO_LIST,false);
            newsListFragment.setArguments(bundle);
            mFragmentList.add(newsListFragment);//添加到集合中
        }
    }

    @Override
    public void initListener() {

        ChannelPagerAdapter channelPagerAdapter = new ChannelPagerAdapter(mFragmentList,mChannelList,getChildFragmentManager());
        mVpContent.setAdapter(channelPagerAdapter);
        mVpContent.setOffscreenPageLimit(mFragmentList.size());

        mVpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当页签切换的时候，如果有播放视频，则释放资源
                Jzvd.releaseAllVideos();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public String getCurrentChannelCode(){
        int currentItem = mVpContent.getCurrentItem();
        return mChannelList.get(currentItem).channelCode;
    }

    @Override
    public void loadData() {
        KLog.i("loadData");
    }


}
