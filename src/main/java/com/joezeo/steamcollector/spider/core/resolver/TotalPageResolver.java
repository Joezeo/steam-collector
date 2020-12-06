package com.joezeo.steamcollector.spider.core.resolver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 解析获取Steam各个搜索页的总页数
 *
 * @author Joezeo
 * @date 2020/12/6 16:13
 */
@Component
public class TotalPageResolver extends AbstractPageResolver{
    @Override
    public int resolveNumber(String page, Map<String, Object> params) {
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

    @Override
    public void resolve(String page, Map<String, Object> params) {

    }
}
