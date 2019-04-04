package com.chaychan.news.presenter;

import com.chaychan.news.model.entity.News;
import com.chaychan.news.model.entity.NewsData;
import com.chaychan.news.model.response.NewsResponse;
import com.chaychan.news.base.BasePresenter;
import com.chaychan.news.utils.ListUtils;
import com.chaychan.news.utils.PreUtils;
import com.chaychan.news.presenter.view.lNewsListView;
import com.google.gson.Gson;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * @author ChayChan
 * @description: 新闻列表的presenter
 * @date 2017/6/18  10:04
 */

public class NewsListPresenter extends BasePresenter<lNewsListView> {

    private long lastTime;

    public NewsListPresenter(lNewsListView view) {
        super(view);
    }


    public void getNewsList(String channelCode){
        lastTime = PreUtils.getLong(channelCode,0);//读取对应频道下最后一次刷新的时间戳
        if (lastTime == 0){
            //如果为空，则是从来没有刷新过，使用当前时间戳
            lastTime = System.currentTimeMillis() / 1000;
        }

        addSubscription(mApiService.getNewsList(channelCode,lastTime,System.currentTimeMillis()/1000), new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                KLog.e(e.getLocalizedMessage());
                mView.onError();
            }

            @Override
            public void onNext(NewsResponse response) {
                lastTime = System.currentTimeMillis() / 1000;
                PreUtils.putLong(channelCode,lastTime);//保存刷新的时间戳

//                List<NewsData> data = response.data;
                List<NewsData> data = new ArrayList<>();
                NewsData newsData = new NewsData();
                newsData.setCode("");
                newsData.setContent("{\"abstract\":\"飞跃～惊险刺激的普吉岛高空滑翔！哇！真香（想）的海鲜呀！\",\"action_list\":[{\"action\":1,\"desc\":\"\",\"extra\":{}},{\"action\":3,\"desc\":\"\",\"extra\":{}},{\"action\":7,\"desc\":\"\",\"extra\":{}},{\"action\":9,\"desc\":\"\",\"extra\":{}}],\"aggr_type\":1,\"allow_download\":false,\"article_sub_type\":0,\"article_type\":0,\"article_url\":\"http://toutiao.com/group/6660779893971747342/\",\"article_version\":0,\"ban_comment\":0,\"behot_time\":1554373993,\"bury_count\":10,\"cell_flag\":262155,\"cell_layout_style\":1,\"cell_type\":0,\"comment_count\":291,\"content_decoration\":\"\",\"control_panel\":{\"recommend_sponsor\":{\"icon_url\":\"http://p3-tt.bytecdn.cn/origin/13ef000096960314fff4\",\"label\":\"帮上头条\",\"night_icon_url\":\"http://p3-tt.bytecdn.cn/origin/dc1d0001ad958473e24b\",\"target_url\":\"https://i.snssdk.com/ad/pgc_promotion/mobile/create/?group_id=6660779893971747342\\u0026item_id=6660779893971747342\"}},\"cursor\":1554373993000,\"digg_count\":2359,\"display_url\":\"http://toutiao.com/group/6660779893971747342/\",\"filter_words\":[{\"id\":\"8:0\",\"is_selected\":false,\"name\":\"看过了\"},{\"id\":\"9:1\",\"is_selected\":false,\"name\":\"内容太水\"},{\"id\":\"5:1030599447\",\"is_selected\":false,\"name\":\"拉黑作者:密子君\"},{\"id\":\"1:307819565\",\"is_selected\":false,\"name\":\"不想看:旅游视频\"},{\"id\":\"6:2459753548\",\"is_selected\":false,\"name\":\"不想看:春节泰好玩\"}],\"forward_info\":{\"forward_count\":14},\"group_flags\":32832,\"group_id\":6660779893971747342,\"group_source\":2,\"has_m3u8_video\":false,\"has_mp4_video\":0,\"has_video\":true,\"hot\":0,\"ignore_web_transform\":1,\"interaction_data\":\"\",\"is_subject\":false,\"item_id\":6660779893971747342,\"item_version\":0,\"keywords\":\"海岛,攻略,泰国,普吉岛,海鲜\",\"large_image_list\":[{\"height\":326,\"uri\":\"video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\",\"url\":\"http://p9-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\",\"url_list\":[{\"url\":\"http://p9-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\"},{\"url\":\"http://p6-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\"},{\"url\":\"http://p3-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\"}],\"width\":580}],\"level\":0,\"log_pb\":{\"impr_id\":\"20190404201813010008035045046AEC2\",\"is_following\":\"0\"},\"media_info\":{\"avatar_url\":\"http://p10.pstatp.com/large/93310002d96b05424cd4\",\"follow\":false,\"is_star_user\":false,\"media_id\":7006141067,\"name\":\"密子君\",\"recommend_reason\":\"\",\"recommend_type\":0,\"user_id\":7006141067,\"user_verified\":true,\"verified_content\":\"\"},\"media_name\":\"密子君\",\"middle_image\":{\"height\":360,\"uri\":\"list/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\",\"url\":\"http://p3-tt.bytecdn.cn/list/300x196/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803.webp\",\"url_list\":[{\"url\":\"http://p3-tt.bytecdn.cn/list/300x196/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803.webp\"},{\"url\":\"http://p1-tt.bytecdn.cn/list/300x196/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803.webp\"},{\"url\":\"http://p97-tt.bytecdn.cn/list/300x196/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803.webp\"}],\"width\":640},\"need_client_impr_recycle\":1,\"play_auth_token\":\"HMAC-SHA1:2.0:1554466693178702880:bab42eac5b9e4a8eb25a91fc371ad533:/haTeYsY6e1b8TwJ5aKkOe9a9Yk=\",\"play_biz_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTQ0NjY2OTMsInZlciI6InYxIiwiYWsiOiJiYWI0MmVhYzViOWU0YThlYjI1YTkxZmMzNzFhZDUzMyIsInN1YiI6InBnY18xMDgwcCJ9.ZGEKt9UVDVEqbilH-GFX49px2ZaUYWNSXHFJKuwjQiE\",\"publish_time\":1550833670,\"read_count\":36709,\"repin_count\":234,\"rid\":\"20190404201813010008035045046AEC2\",\"share_count\":42,\"share_info\":{\"cover_image\":null,\"description\":null,\"on_suppress\":0,\"share_type\":{\"pyq\":0,\"qq\":0,\"qzone\":0,\"wx\":0},\"share_url\":\"http://m.toutiao.com/a6660779893971747342/?app=news_article\\u0026is_hit_share_recommend=0\",\"title\":\"泰国滑翔跳岛、体验海岛深潜攻略！一定要去一次！刺激又美丽\",\"token_type\":1,\"weixin_cover_image\":{\"height\":2009,\"uri\":\"large/tos-cn-i-0000/1a8438ac-3692-11e9-ae71-7cd30ab189d0\",\"url\":\"http://p6-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/1a8438ac-3692-11e9-ae71-7cd30ab189d0\",\"url_list\":[{\"url\":\"http://p6-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/1a8438ac-3692-11e9-ae71-7cd30ab189d0\"},{\"url\":\"http://p1-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/1a8438ac-3692-11e9-ae71-7cd30ab189d0\"},{\"url\":\"http://p6-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/1a8438ac-3692-11e9-ae71-7cd30ab189d0\"}],\"width\":1920}},\"share_type\":2,\"share_url\":\"http://m.toutiao.com/a6660779893971747342/?app=news_article\\u0026is_hit_share_recommend=0\",\"show_dislike\":true,\"show_portrait\":false,\"show_portrait_article\":false,\"source\":\"密子君\",\"source_icon_style\":5,\"source_open_url\":\"sslocal://profile?refer=video\\u0026uid=7006141067\",\"tag\":\"video_travel\",\"tag_id\":6660779893971747342,\"tip\":0,\"title\":\"泰国滑翔跳岛、体验海岛深潜攻略！一定要去一次！刺激又美丽\",\"ugc_recommend\":{\"activity\":\"\",\"reason\":\"知名美食领域创作者\"},\"url\":\"http://toutiao.com/group/6660779893971747342/\",\"user_info\":{\"avatar_url\":\"http://p1.pstatp.com/thumb/93310002d96b05424cd4\",\"description\":\"带你一起密食天下，做个快乐的吃货！合作QQ：2727844336\",\"follow\":false,\"follower_count\":0,\"live_info_type\":1,\"name\":\"密子君\",\"schema\":\"sslocal://profile?uid=7006141067\\u0026refer=video\",\"user_auth_info\":\"{\\\"auth_type\\\":\\\"1\\\",\\\"auth_info\\\":\\\"知名美食领域创作者\\\",\\\"other_auth\\\":{\\\"interest\\\":\\\"知名美食领域创作者\\\"}}\",\"user_id\":7006141067,\"user_verified\":true,\"verified_content\":\"知名美食领域创作者\"},\"user_repin\":0,\"user_verified\":1,\"verified_content\":\"知名美食领域创作者\",\"video_detail_info\":{\"detail_video_large_image\":{\"height\":326,\"uri\":\"video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\",\"url\":\"http://p97-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\",\"url_list\":[{\"url\":\"http://p97-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\"},{\"url\":\"http://p1-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\"},{\"url\":\"http://p6-tt.bytecdn.cn/video1609/tos-cn-i-0004/c5e0e8d8a1cf421d9a636a1135a8d803\"}],\"width\":580},\"direct_play\":1,\"group_flags\":32832,\"show_pgc_subscribe\":1,\"video_id\":\"v03004850000bhnte7j70kfha9mia3n0\",\"video_preloading_flag\":1,\"video_type\":0,\"video_watch_count\":54995,\"video_watching_count\":0},\"video_duration\":678,\"video_id\":\"v03004850000bhnte7j70kfha9mia3n0\",\"video_style\":0}");
                data.add(newsData);
                NewsData newsData1 = new NewsData();
                newsData1.setCode("");
                newsData1.setContent("{\"abstract\":\"林宥嘉与模唱者合唱《说谎》，声音太像，大家都猜不出哪个是原唱\",\"action_list\":[{\"action\":1,\"desc\":\"\",\"extra\":{}},{\"action\":3,\"desc\":\"\",\"extra\":{}},{\"action\":7,\"desc\":\"\",\"extra\":{}},{\"action\":9,\"desc\":\"\",\"extra\":{}}],\"aggr_type\":1,\"allow_download\":false,\"article_sub_type\":0,\"article_type\":0,\"article_url\":\"http://toutiao.com/group/6669942773203862023/\",\"article_version\":0,\"ban_comment\":0,\"behot_time\":1554375793,\"bury_count\":1,\"cell_flag\":262155,\"cell_layout_style\":1,\"cell_type\":0,\"comment_count\":174,\"content_decoration\":\"\",\"control_panel\":{\"recommend_sponsor\":{\"icon_url\":\"http://p3-tt.bytecdn.cn/origin/13ef000096960314fff4\",\"label\":\"帮上头条\",\"night_icon_url\":\"http://p3-tt.bytecdn.cn/origin/dc1d0001ad958473e24b\",\"target_url\":\"https://i.snssdk.com/ad/pgc_promotion/mobile/create/?group_id=6669942773203862023\\u0026item_id=6669942773203862023\"}},\"cursor\":1554375793000,\"digg_count\":771,\"display_url\":\"http://toutiao.com/group/6669942773203862023/\",\"filter_words\":[{\"id\":\"8:0\",\"is_selected\":false,\"name\":\"看过了\"},{\"id\":\"9:1\",\"is_selected\":false,\"name\":\"内容太水\"},{\"id\":\"5:1093253529\",\"is_selected\":false,\"name\":\"拉黑作者:浙江卫视综艺\"},{\"id\":\"1:181591924\",\"is_selected\":false,\"name\":\"不想看:音乐视频\"},{\"id\":\"6:2462835\",\"is_selected\":false,\"name\":\"不想看:林宥嘉\"}],\"forward_info\":{\"forward_count\":7},\"group_flags\":32832,\"group_id\":6669942773203862023,\"group_source\":2,\"has_m3u8_video\":false,\"has_mp4_video\":0,\"has_video\":true,\"hot\":0,\"ignore_web_transform\":1,\"interaction_data\":\"\",\"is_subject\":false,\"item_id\":6669942773203862023,\"item_version\":0,\"keywords\":\"原唱,说谎,模唱者,林宥嘉\",\"large_image_list\":[{\"height\":326,\"uri\":\"video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\",\"url\":\"http://p3-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\",\"url_list\":[{\"url\":\"http://p3-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\"},{\"url\":\"http://p97-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\"},{\"url\":\"http://p9-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\"}],\"width\":580}],\"level\":0,\"log_pb\":{\"impr_id\":\"20190404201813010008035045046AEC2\",\"is_following\":\"0\"},\"media_info\":{\"avatar_url\":\"http://p9.pstatp.com/large/18a20010237e06bc9622\",\"follow\":false,\"is_star_user\":false,\"media_id\":1562466769975297,\"name\":\"浙江卫视综艺\",\"recommend_reason\":\"\",\"recommend_type\":0,\"user_id\":58363379363,\"user_verified\":true,\"verified_content\":\"\"},\"media_name\":\"浙江卫视综艺\",\"middle_image\":{\"height\":360,\"uri\":\"list/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\",\"url\":\"http://p9-tt.bytecdn.cn/list/300x196/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e.webp\",\"url_list\":[{\"url\":\"http://p9-tt.bytecdn.cn/list/300x196/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e.webp\"},{\"url\":\"http://p6-tt.bytecdn.cn/list/300x196/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e.webp\"},{\"url\":\"http://p3-tt.bytecdn.cn/list/300x196/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e.webp\"}],\"width\":640},\"need_client_impr_recycle\":1,\"play_auth_token\":\"HMAC-SHA1:2.0:1554466693173935471:bab42eac5b9e4a8eb25a91fc371ad533:8tomv9pPa+h8IQKq528iKJ5iUSI=\",\"play_biz_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTQ0NjY2OTMsInZlciI6InYxIiwiYWsiOiJiYWI0MmVhYzViOWU0YThlYjI1YTkxZmMzNzFhZDUzMyIsInN1YiI6InBnY18xMDgwcCJ9.rI5BZ25Etthxhec1_2gQ1U8jBGB2HIwkyWdJU9zs2p8\",\"publish_time\":1552967069,\"read_count\":50597,\"repin_count\":454,\"rid\":\"20190404201813010008035045046AEC2\",\"share_count\":25,\"share_info\":{\"cover_image\":null,\"description\":null,\"on_suppress\":0,\"share_type\":{\"pyq\":0,\"qq\":0,\"qzone\":0,\"wx\":0},\"share_url\":\"http://m.toutiao.com/a6669942773203862023/?app=news_article\\u0026is_hit_share_recommend=0\",\"title\":\"林宥嘉与模唱者合唱《说谎》，声音太像，大家都猜不出哪个是原唱\",\"token_type\":1,\"weixin_cover_image\":{\"height\":1075,\"uri\":\"large/tos-cn-i-0000/4dcfa686-49f9-11e9-84e7-7cd30a50312e\",\"url\":\"http://p6-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/4dcfa686-49f9-11e9-84e7-7cd30a50312e\",\"url_list\":[{\"url\":\"http://p6-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/4dcfa686-49f9-11e9-84e7-7cd30a50312e\"},{\"url\":\"http://p6-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/4dcfa686-49f9-11e9-84e7-7cd30a50312e\"},{\"url\":\"http://p97-tt.bytecdn.cn/list/640x360/tos-cn-i-0000/4dcfa686-49f9-11e9-84e7-7cd30a50312e\"}],\"width\":852}},\"share_type\":2,\"share_url\":\"http://m.toutiao.com/a6669942773203862023/?app=news_article\\u0026is_hit_share_recommend=0\",\"show_dislike\":true,\"show_portrait\":false,\"show_portrait_article\":false,\"source\":\"浙江卫视综艺\",\"source_icon_style\":1,\"source_open_url\":\"sslocal://profile?refer=video\\u0026uid=58363379363\",\"tag\":\"video_music\",\"tag_id\":6669942773203862023,\"tip\":0,\"title\":\"林宥嘉与模唱者合唱《说谎》，声音太像，大家都猜不出哪个是原唱\",\"ugc_recommend\":{\"activity\":\"\",\"reason\":\"浙江卫视综艺官方账号\"},\"url\":\"http://toutiao.com/group/6669942773203862023/\",\"user_info\":{\"avatar_url\":\"http://p3.pstatp.com/thumb/18a20010237e06bc9622\",\"description\":\"荟萃浙江卫视综艺经典，传播中国蓝永续正能量。\",\"follow\":false,\"follower_count\":0,\"live_info_type\":1,\"name\":\"浙江卫视综艺\",\"schema\":\"sslocal://profile?uid=58363379363\\u0026refer=video\",\"user_auth_info\":\"{\\\"auth_type\\\":\\\"0\\\",\\\"auth_info\\\":\\\"浙江卫视综艺官方账号\\\"}\",\"user_id\":58363379363,\"user_verified\":true,\"verified_content\":\"浙江卫视综艺官方账号\"},\"user_repin\":0,\"user_verified\":1,\"verified_content\":\"浙江卫视综艺官方账号\",\"video_detail_info\":{\"detail_video_large_image\":{\"height\":326,\"uri\":\"video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\",\"url\":\"http://p97-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\",\"url_list\":[{\"url\":\"http://p97-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\"},{\"url\":\"http://p1-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\"},{\"url\":\"http://p97-tt.bytecdn.cn/video1609/tos-cn-p-0000/7cca28707c5743a4b71cc5f0c474527e\"}],\"width\":580},\"direct_play\":1,\"group_flags\":32832,\"show_pgc_subscribe\":1,\"video_id\":\"v02004470000bi865u9um7lndktq9m0g\",\"video_preloading_flag\":1,\"video_type\":0,\"video_watch_count\":65005,\"video_watching_count\":0},\"video_duration\":347,\"video_id\":\"v02004470000bi865u9um7lndktq9m0g\",\"video_style\":0}");
                data.add(newsData1);
                List<News> newsList = new ArrayList<>();
                if (!ListUtils.isEmpty(data)){
                    for (NewsData newsData2 : data) {
                        News news = new Gson().fromJson(newsData2.content, News.class);
                        newsList.add(news);
                    }
                }
                KLog.e(newsList);
                mView.onGetNewsListSuccess(newsList,"已刷新");
            }
        });
    }
}
