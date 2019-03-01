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
                newsData.setContent("{\"abstract\":\"来源:新华社“新华视点”微博2016年2月19日，党的新闻舆论工作座谈会召开，习近平总书记发表重要讲话。\",\"action_list\":[{\"action\":1,\"desc\":\"\",\"extra\":{}},{\"action\":3,\"desc\":\"\",\"extra\":{}},{\"action\":7,\"desc\":\"\",\"extra\":{}},{\"action\":9,\"desc\":\"\",\"extra\":{}}],\"aggr_type\":1,\"allow_download\":false,\"article_sub_type\":0,\"article_type\":0,\"article_url\":\"http://www.xinhuanet.com/politics/2019-02/19/c_1124136073.htm\",\"article_version\":0,\"ban_comment\":0,\"behot_time\":1550655644,\"bury_count\":0,\"cell_flag\":262155,\"cell_layout_style\":1,\"cell_type\":0,\"comment_count\":510,\"content_decoration\":\"\",\"cursor\":1550655644000,\"digg_count\":2083,\"display_url\":\"http://toutiao.com/group/6659632147122356739/\",\"filter_words\":[{\"id\":\"8:0\",\"is_selected\":false,\"name\":\"看过了\"},{\"id\":\"9:1\",\"is_selected\":false,\"name\":\"内容太水\"},{\"id\":\"5:9103673\",\"is_selected\":false,\"name\":\"拉黑作者:新华网\"},{\"id\":\"1:1641\",\"is_selected\":false,\"name\":\"不想看:时政\"},{\"id\":\"6:2400572\",\"is_selected\":false,\"name\":\"不想看:新闻\"}],\"forward_info\":{\"forward_count\":25},\"group_id\":6659632147122356739,\"has_m3u8_video\":false,\"has_mp4_video\":0,\"has_video\":false,\"hot\":0,\"ignore_web_transform\":1,\"interaction_data\":\"\",\"is_stick\":true,\"is_subject\":false,\"item_id\":6659632147122356739,\"item_version\":0,\"keywords\":\"习近平总书记,2 • 19,习近平\",\"label\":\"置顶\",\"label_extra\":{\"icon_url\":{},\"is_redirect\":false,\"redirect_url\":\"\",\"style_type\":0},\"label_style\":1,\"level\":0,\"log_pb\":{\"impr_id\":\"20190220174044010025064215438C8D3\",\"is_following\":\"0\"},\"media_info\":{\"avatar_url\":\"http://p2.pstatp.com/large/3658/7378365093\",\"follow\":false,\"is_star_user\":false,\"media_id\":4377795668,\"name\":\"新华网\",\"recommend_reason\":\"\",\"recommend_type\":0,\"user_id\":4377795668,\"user_verified\":true,\"verified_content\":\"\"},\"media_name\":\"新华网\",\"need_client_impr_recycle\":1,\"publish_time\":1550566439,\"read_count\":288490,\"repin_count\":2136,\"rid\":\"20190220174044010025064215438C8D3\",\"share_count\":1175,\"share_info\":{\"cover_image\":null,\"description\":null,\"on_suppress\":0,\"share_type\":{\"pyq\":0,\"qq\":0,\"qzone\":0,\"wx\":0},\"share_url\":\"http://m.toutiao.com/group/6659632147122356739/?iid=13136511752\\u0026app=news_article\\u0026is_hit_share_recommend=0\",\"title\":\"牢记习近平总书记谆谆教诲 做好党的新闻舆论工作\",\"token_type\":1,\"weixin_cover_image\":{\"height\":851,\"uri\":\"large/tos-cn-i-0000/e85c88e0-3423-11e9-a5d7-0cc47af3d934\",\"url\":\"http://p9-tt.bytecdn.cn/large/tos-cn-i-0000/e85c88e0-3423-11e9-a5d7-0cc47af3d934\",\"url_list\":[{\"url\":\"http://p9-tt.bytecdn.cn/large/tos-cn-i-0000/e85c88e0-3423-11e9-a5d7-0cc47af3d934\"},{\"url\":\"http://p1-tt.bytecdn.cn/large/tos-cn-i-0000/e85c88e0-3423-11e9-a5d7-0cc47af3d934\"},{\"url\":\"http://p3-tt.bytecdn.cn/large/tos-cn-i-0000/e85c88e0-3423-11e9-a5d7-0cc47af3d934\"}],\"width\":600}},\"share_url\":\"http://m.toutiao.com/group/6659632147122356739/?iid=13136511752\\u0026app=news_article\\u0026is_hit_share_recommend=0\",\"show_dislike\":false,\"show_portrait\":false,\"show_portrait_article\":false,\"source\":\"新华网\",\"source_icon_style\":2,\"source_open_url\":\"sslocal://profile?uid=4377795668\",\"stick_label\":\"置顶\",\"stick_style\":1,\"tag\":\"news_politics\",\"tag_id\":6659632147122356739,\"tip\":0,\"title\":\"牢记习近平总书记谆谆教诲 做好党的新闻舆论工作\",\"ugc_recommend\":{\"activity\":\"\",\"reason\":\"新华网官方账号\"},\"url\":\"http://www.xinhuanet.com/politics/2019-02/19/c_1124136073.htm\",\"user_info\":{\"avatar_url\":\"http://p3.pstatp.com/thumb/3658/7378365093\",\"description\":\"传播中国，报道世界；权威声音，亲切表达。\",\"follow\":false,\"follower_count\":0,\"live_info_type\":1,\"name\":\"新华网\",\"schema\":\"sslocal://profile?uid=4377795668\\u0026refer=all\",\"user_auth_info\":\"{\\\"auth_type\\\":\\\"0\\\",\\\"auth_info\\\":\\\"新华网官方账号\\\"}\",\"user_id\":4377795668,\"user_verified\":true,\"verified_content\":\"新华网官方账号\"},\"user_repin\":0,\"user_verified\":1,\"verified_content\":\"新华网官方账号\",\"video_style\":0}");
                data.add(newsData);
                NewsData newsData1 = new NewsData();
                newsData1.setCode("");
                newsData1.setContent("{\"abstract\":\"锦鲤猎手4连红啦，继续努力！\",\"action_list\":[{\"action\":1,\"desc\":\"\",\"extra\":{}},{\"action\":3,\"desc\":\"\",\"extra\":{}},{\"action\":7,\"desc\":\"\",\"extra\":{}},{\"action\":9,\"desc\":\"\",\"extra\":{}}],\"aggr_type\":1,\"allow_download\":false,\"article_sub_type\":0,\"article_type\":0,\"article_url\":\"http://toutiao.com/group/6662970019531457028/\",\"article_version\":0,\"ban_comment\":0,\"behot_time\":1551337755,\"bury_count\":0,\"cell_flag\":262155,\"cell_layout_style\":1,\"cell_type\":0,\"comment_count\":0,\"content_decoration\":\"\",\"control_panel\":{\"recommend_sponsor\":{\"icon_url\":\"http://p3-tt.bytecdn.cn/origin/13ef000096960314fff4\",\"label\":\"帮上头条\",\"night_icon_url\":\"http://p3-tt.bytecdn.cn/origin/dc1d0001ad958473e24b\",\"target_url\":\"https://i.snssdk.com/ad/pgc_promotion/mobile/create/?group_id=6662970019531457028\\u0026item_id=6662970019531457028\"}},\"cursor\":1551337755000,\"digg_count\":0,\"display_url\":\"http://toutiao.com/group/6662970019531457028/\",\"filter_words\":[{\"id\":\"8:0\",\"is_selected\":false,\"name\":\"看过了\"},{\"id\":\"9:1\",\"is_selected\":false,\"name\":\"内容太水\"},{\"id\":\"5:290378454\",\"is_selected\":false,\"name\":\"拉黑作者:竞彩猫\"},{\"id\":\"1:181567883\",\"is_selected\":false,\"name\":\"不想看:游戏视频\"},{\"id\":\"6:22964\",\"is_selected\":false,\"name\":\"不想看:锦鲤\"}],\"forward_info\":{\"forward_count\":0},\"group_flags\":32832,\"group_id\":6662970019531457028,\"has_m3u8_video\":false,\"has_mp4_video\":0,\"has_video\":true,\"hot\":0,\"ignore_web_transform\":1,\"interaction_data\":\"\",\"is_subject\":false,\"item_id\":6662970019531457028,\"item_version\":0,\"keywords\":\"两兄弟,锦鲤,红红红\",\"large_image_list\":[{\"height\":326,\"uri\":\"video1609/1b86200049a48faa13670\",\"url\":\"http://p9-tt.bytecdn.cn/video1609/1b86200049a48faa13670\",\"url_list\":[{\"url\":\"http://p9-tt.bytecdn.cn/video1609/1b86200049a48faa13670\"},{\"url\":\"http://p9-tt.bytecdn.cn/video1609/1b86200049a48faa13670\"},{\"url\":\"http://p9-tt.bytecdn.cn/video1609/1b86200049a48faa13670\"}],\"width\":580}],\"level\":0,\"log_pb\":{\"impr_id\":\"2019022817241501001203522872202AB\",\"is_following\":\"0\"},\"media_info\":{\"avatar_url\":\"http://p3.pstatp.com/large/bc2000190fe09bb9a32\",\"follow\":false,\"is_star_user\":false,\"media_id\":5547086120,\"name\":\"竞彩猫\",\"recommend_reason\":\"\",\"recommend_type\":0,\"user_id\":5547086120,\"user_verified\":true,\"verified_content\":\"\"},\"media_name\":\"竞彩猫\",\"middle_image\":{\"height\":360,\"uri\":\"list/1b86200049a48faa13670\",\"url\":\"http://p1-tt.bytecdn.cn/list/300x196/1b86200049a48faa13670.webp\",\"url_list\":[{\"url\":\"http://p1-tt.bytecdn.cn/list/300x196/1b86200049a48faa13670.webp\"},{\"url\":\"http://p9-tt.bytecdn.cn/list/300x196/1b86200049a48faa13670.webp\"},{\"url\":\"http://p3-tt.bytecdn.cn/list/300x196/1b86200049a48faa13670.webp\"}],\"width\":640},\"need_client_impr_recycle\":1,\"play_auth_token\":\"HMAC-SHA1:2.0:1551432255852160434:bab42eac5b9e4a8eb25a91fc371ad533:pL39nmJPiZGR5yzVNzV+tFCRIVE=\",\"play_biz_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTE0MzIyNTUsInZlciI6InYxIiwiYWsiOiJiYWI0MmVhYzViOWU0YThlYjI1YTkxZmMzNzFhZDUzMyIsInN1YiI6InBnY18xMDgwcCJ9.MaNfNYCDH6or11CP7Wtc8SP1nyvz7lq3YwN7Qt3AS1I\",\"publish_time\":1551343598,\"read_count\":6,\"rid\":\"2019022817241501001203522872202AB\",\"share_count\":0,\"share_info\":{\"cover_image\":null,\"description\":null,\"on_suppress\":0,\"share_type\":{\"pyq\":0,\"qq\":0,\"qzone\":0,\"wx\":0},\"share_url\":\"http://m.toutiao.com/a6662970019531457028/?iid=13136511752\\u0026app=news_article\\u0026is_hit_share_recommend=0\",\"title\":\"锦鲤猎手豪取4连红！感谢大家支持，两兄弟会继续带大家红红红！\",\"token_type\":1,\"weixin_cover_image\":{\"height\":1455,\"uri\":\"large/tos-cn-i-0000/5e609186-3b35-11e9-b563-0cc47ad2a5f0\",\"url\":\"http://p9-tt.bytecdn.cn/large/tos-cn-i-0000/5e609186-3b35-11e9-b563-0cc47ad2a5f0\",\"url_list\":[{\"url\":\"http://p9-tt.bytecdn.cn/large/tos-cn-i-0000/5e609186-3b35-11e9-b563-0cc47ad2a5f0\"},{\"url\":\"http://p1-tt.bytecdn.cn/large/tos-cn-i-0000/5e609186-3b35-11e9-b563-0cc47ad2a5f0\"},{\"url\":\"http://p6-tt.bytecdn.cn/large/tos-cn-i-0000/5e609186-3b35-11e9-b563-0cc47ad2a5f0\"}],\"width\":1280}},\"share_type\":2,\"share_url\":\"http://m.toutiao.com/a6662970019531457028/?iid=13136511752\\u0026app=news_article\\u0026is_hit_share_recommend=0\",\"show_dislike\":true,\"show_portrait\":false,\"show_portrait_article\":false,\"source\":\"竞彩猫\",\"source_icon_style\":4,\"source_open_url\":\"sslocal://profile?refer=video\\u0026uid=5547086120\",\"tag\":\"video_game\",\"tag_id\":6662970019531457028,\"tip\":0,\"title\":\"锦鲤猎手豪取4连红！感谢大家支持，两兄弟会继续带大家红红红！\",\"ugc_recommend\":{\"activity\":\"\",\"reason\":\"优质体育领域创作者\"},\"url\":\"http://toutiao.com/group/6662970019531457028/\",\"user_info\":{\"avatar_url\":\"http://p3.pstatp.com/thumb/bc2000190fe09bb9a32\",\"description\":\"众多体育界大咖为彩民提供专业的竞彩足球、篮球赛事分析\",\"follow\":false,\"follower_count\":0,\"live_info_type\":1,\"name\":\"竞彩猫\",\"schema\":\"sslocal://profile?uid=5547086120\\u0026refer=video\",\"user_auth_info\":\"{\\\"auth_type\\\":\\\"0\\\",\\\"auth_info\\\":\\\"优质体育领域创作者\\\",\\\"other_auth\\\":{\\\"interest\\\":\\\"优质体育领域创作者\\\"}}\",\"user_id\":5547086120,\"user_verified\":true,\"verified_content\":\"优质体育领域创作者\"},\"user_repin\":0,\"user_verified\":1,\"verified_content\":\"优质体育领域创作者\",\"video_detail_info\":{\"detail_video_large_image\":{\"height\":326,\"uri\":\"video1609/1b86200049a48faa13670\",\"url\":\"http://p6-tt.bytecdn.cn/video1609/1b86200049a48faa13670\",\"url_list\":[{\"url\":\"http://p6-tt.bytecdn.cn/video1609/1b86200049a48faa13670\"},{\"url\":\"http://p1-tt.bytecdn.cn/video1609/1b86200049a48faa13670\"},{\"url\":\"http://p9-tt.bytecdn.cn/video1609/1b86200049a48faa13670\"}],\"width\":580},\"direct_play\":1,\"group_flags\":32832,\"show_pgc_subscribe\":1,\"video_id\":\"v03004130000bhrpv91ot002q7556230\",\"video_preloading_flag\":1,\"video_type\":0,\"video_watch_count\":12,\"video_watching_count\":0},\"video_duration\":210,\"video_id\":\"v03004130000bhrpv91ot002q7556230\",\"video_style\":0}");
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
