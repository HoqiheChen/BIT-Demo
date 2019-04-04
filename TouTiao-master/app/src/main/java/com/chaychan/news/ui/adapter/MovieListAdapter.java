package com.chaychan.news.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.news.R;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.utils.TimeUtils;


import java.util.List;

/*
*    Author:GeChen
*    Date:2019/4/4
*    Description:电影适配器
*/

public class MovieListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {

    public MovieListAdapter(@Nullable List<News> data) {
        super(R.layout.item_movie_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News news) {
        if (TextUtils.isEmpty(news.title)) {
            //如果没有标题，则直接跳过
            return;
        }

        helper.setVisible(R.id.ll_title, true);//显示标题栏
        helper.setText(R.id.tv_title, "建党伟业");
        helper.setText(R.id.tv_type, "剧情片");
        helper.setBackgroundRes(R.id.ll_title,R.mipmap.cover_movie);
        helper.setText(R.id.tv_info,"庆祝中共建党九十周年的献礼影片。");
        helper.setVisible(R.id.ll_duration, true)//显示时长
                .setText(R.id.tv_duration, TimeUtils.secToTime(news.video_duration));//设置时长


    }
}
