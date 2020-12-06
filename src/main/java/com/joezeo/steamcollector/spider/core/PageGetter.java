package com.joezeo.steamcollector.spider.core;

import com.joezeo.steamcollector.spider.core.resolver.AppInfoResolver;
import com.joezeo.steamcollector.spider.core.resolver.SearchingPageUrlResolver;
import com.joezeo.steamcollector.spider.core.resolver.SpecialPriceResolver;
import com.joezeo.steamcollector.spider.core.resolver.TotalPageResolver;
import com.joezeo.steamcollector.spider.enums.SpiderJob;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 构造http请求获取页面信息
 *
 * @author JoeZane
 */
@Service
@Slf4j
@AllArgsConstructor
public class PageGetter {

    private final OkHttpClient client4Steam;

    private final FailureUrlCollector urlCollector;

    private final TotalPageResolver totalPageResolver;

    private final SearchingPageUrlResolver searchingPageUrlResolver;

    private final AppInfoResolver appInfoResolver;

    private final SpecialPriceResolver specialPriceResolver;

    public void spiderUrlAsyn(String url, String type, Integer appid, SpiderJob jobType) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                // 认证年龄
                .addHeader("cookie", "birthtime=470678401")
                // 设置语言
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .get()
                .build();

        Call call = client4Steam.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("爬取网页失败： 可能等待时间过长，将再次重试 => " + url);
                urlCollector.addFailure(url, type);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String page = Objects.requireNonNull(response.body()).string();
                log.info("获取页面成功 ： {}", url);
                Map<String, Object> params = new HashMap<>();

                switch (jobType) {
                    case INIT_URL_DATA:
                    case DAILY_CHECK_URL:
                        params.put("type", type);
                        searchingPageUrlResolver.resolve(page, params);
                        break;

                    case INIT_APP_INFO:
                    case DAILY_CHECK_APP_INFO:
                        params.put("type", type);
                        params.put("appid", appid);
                        params.put("isSub", url.lastIndexOf("/sub/") != -1);
                        appInfoResolver.resolve(page, params);
                        break;

                    case DAILY_SPIDE_SPECIAL_PRICE:
                        params.put("url", url);
                        params.put("appid", appid);
                        specialPriceResolver.resolve(page, params);
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public int getSteamTotalPage(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                // 认证年龄
                .addHeader("cookie", "birthtime=470678401")
                // 设置语言
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .get()
                .build();

        Call call = client4Steam.newCall(request);
        // 这里不能使用异步获取，因为这里获得的总页数在接下来的逻辑中是有用的
        try {
            Response response = call.execute();
            return totalPageResolver.resolveNumber(Objects.requireNonNull(response.body()).string(), null);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
