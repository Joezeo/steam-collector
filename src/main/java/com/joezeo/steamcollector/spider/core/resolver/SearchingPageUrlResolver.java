package com.joezeo.steamcollector.spider.core.resolver;

import com.joezeo.steamcollector.common.utils.CastUtil;
import com.joezeo.steamcollector.spider.dao.SteamAppUrlDao;
import com.joezeo.steamcollector.spider.enums.UrlType;
import com.joezeo.steamcollector.spider.vo.SteamAppUrl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Joezeo
 * @date 2020/12/6 16:17
 */
@Slf4j
@Component
public class SearchingPageUrlResolver extends AbstractPageResolver{

    private final SteamAppUrlDao steamAppUrlDao;

    public SearchingPageUrlResolver(SteamAppUrlDao steamAppUrlDao) {
        this.steamAppUrlDao = steamAppUrlDao;
    }
    @Override
    public int resolveNumber(String page, Map<String, Object> params) {
        return 0;
    }

    @Override
    public void resolve(String page, Map<String, Object> params) {
        Document doc = Jsoup.parse(page);

        String type = CastUtil.cast(params.get("type"));

        // 从doc对象获取数据
        Element content = doc.getElementById("search_resultsRows");
        Elements links = content.getElementsByTag("a");
        links.forEach(link -> {
            String appKey = link.attr("data-ds-itemkey");// ep:App_901583
            String appid = appKey.substring(appKey.lastIndexOf("_") + 1);

            String url = link.attr("href");
            // steam 上每天搜索页的 app url 都会加上一个不同的参数，如 ?snr=1_7_7_230_150_1364 ，存储时去掉这个参数
            url = url.substring(0, url.lastIndexOf("?"));

            /*TODO: 这里要做一个全面的重构，做法：将爬取的url放入一个生产消费队列中，消费者取出爬取的url，统一批量放入mongo，避免又一次一次的mongo操作
                    定义两种不同含义的队列，一种数据库队列，队列中存储的信息全部放入数据库中
                                         一种爬取队列，队列中存储的url全部用来爬取消费
                    消费者考虑使用 AKKA 框架 actor 模型
             */

            /*
             * 注意：当天特惠商品的url应当和其他app分开存储
             */
            if (UrlType.SPECIAL_URL.getType().equals(type)) {
                // 特惠商品的url主要存储在内存中，并在mongo中备份，防止内存中的数据意外丢失
            } else {
                List<SteamAppUrl> urlList = steamAppUrlDao.listOfAppidAndType(appid, type);
                if (urlList == null || urlList.size() == 0) {
                    // 说明该app地址不存在,存入数据库中
                    boolean suc = steamAppUrlDao.save(new SteamAppUrl(appid, url, type));
                    if (!suc) {
                        log.error("url地址存储数据库时失败,appid=" + appid);
                    }
                } else if (urlList.size() == 1) {
                    String memUrl = urlList.get(0).getUrl();
                    if (!memUrl.equals(url)) { // 因为steam的礼包（sub）和软件（app）的appid有可能相同,但是url不同
                        boolean suc = steamAppUrlDao.save(new SteamAppUrl(appid, url, type));
                        if (!suc) {
                            log.error("存储App Url失败,appid=" + appid);
                        }
                    }
                }
            }
        });
    }
}
