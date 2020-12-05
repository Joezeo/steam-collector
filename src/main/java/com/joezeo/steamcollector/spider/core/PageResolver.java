package com.joezeo.steamcollector.spider.core;

import com.joezeo.steamcollector.spider.SteamUrlDao;
import com.joezeo.steamcollector.spider.vo.SteamAppUrl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JoeZane
 * @email joezane.cn@gmail.com
 * @date 2020/10/16 16:16
 */
@Slf4j
@Component
public class PageResolver {

    private final SteamUrlDao steamUrlDao;

    public PageResolver(SteamUrlDao steamUrlDao) {
        this.steamUrlDao = steamUrlDao;
    }

    /**
     * 解析获取Steam各个搜索页的总页数
     */
    public int resolvSteamTotalPage(String page) {
        Document doc = Jsoup.parse(page);
        Elements searchDivs = doc.getElementsByClass("search_pagination_right");
        String pageStr = "";
        for (Element e : searchDivs) {
            Elements as = e.getElementsByTag("a");
            int i = 0;
            for (Element a : as) {
                if (i == 2) {
                    pageStr = a.html();
                    break;
                }
                i++;
            }
        }
        return Integer.parseInt(pageStr);
    }

    /**
     * 解析steam搜索页面，获取各个app的url
     * 用于日常/初始化爬取搜索页面
     * 与数据库中的做对比，如果没有的则插入
     */
    public void initOrCheckUrl(String page, String type) {
        Document doc = Jsoup.parse(page);

        // 从doc对象获取数据
        Element content = doc.getElementById("search_resultsRows");
        Elements links = content.getElementsByTag("a");
        links.stream().forEach(link -> {
            String appKey = link.attr("data-ds-itemkey");// ep:App_901583
            String appid = appKey.substring(appKey.lastIndexOf("_") + 1);

            String url = link.attr("href");
            // steam上每天搜索页的app都会加上一个不同的参数，如?snr=1_7_7_230_150_1364，存储时去掉这个参数
            url = url.substring(0, url.lastIndexOf("?"));

            List<SteamAppUrl> urlList = steamUrlDao.listOfAppid(Integer.parseInt(appid), type);
            if (urlList == null || urlList.size() == 0) {
                // 说明该app地址不存在,存入数据库中
                boolean suc = steamUrlDao.save(new SteamAppUrl(appid, url, type));
                if (!suc) {
                    log.error("url地址存储数据库时失败,appid=" + appid);
                }
            } else if (urlList.size() == 1) {
                String memUrl = urlList.get(0).getUrl();
                String newUrl = url;
                if (!memUrl.equals(newUrl)) { // 因为steam的礼包（sub）和软件（app）的appid有可能相同,但是url不同
                    boolean suc = steamUrlDao.save(new SteamAppUrl(appid, url, type));
                    if (!suc) {
                        log.error("存储App Url失败,appid=" + appid);
                    }
                }
            }
        });
    }

    public void initOrCheckAppInfo(String page, String type, Integer appid, boolean b) {

    }

    public void dailySpideSpecialPrice(String url, String page, Integer appid) {

    }
}
