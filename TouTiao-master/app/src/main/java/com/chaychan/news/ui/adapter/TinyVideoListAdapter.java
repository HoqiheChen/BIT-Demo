package com.chaychan.news.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.news.R;
import com.chaychan.news.listener.VideoStateListenerAdapter;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.MyJZVideoPlayerStandard;
import com.chaychan.news.utils.TimeUtils;
import com.chaychan.news.utils.UIUtils;
import com.chaychan.news.utils.VideoPathDecoder;
import com.socks.library.KLog;

import java.util.List;

import cn.jzvd.JzvdStd;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TinyVideoListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {

    public TinyVideoListAdapter(@Nullable List<News> data) {
        super(R.layout.item_tiny_video_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News news) {
        if (TextUtils.isEmpty(news.title)) {
            //如果没有标题，则直接跳过
            return;
        }
        helper.setVisible(R.id.ll_title, true);
        helper.setBackgroundRes(R.id.ll_title,R.mipmap.cover_tiny_video);
        helper.setVisible(R.id.ll_duration, true)//显示时长
                .setText(R.id.tv_duration, TimeUtils.secToTime(news.video_duration));//设置时长
    }
}
