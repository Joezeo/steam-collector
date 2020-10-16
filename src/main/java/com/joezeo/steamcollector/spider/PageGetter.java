package com.joezeo.steamcollector.spider;

import com.joezeo.steamcollector.spider.enums.SpiderJob;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class PageGetter {
    @Autowired
    private OkHttpClient client4Steam;
    @Autowired
    private PageResolver pageResolver;
    @Autowired
    private FailureUrlCollector urlCollector;

    public void spiderUrlAsyn(String url, String type, Integer appid, SpiderJob jobType) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                .addHeader("cookie", "birthtime=470678401") // 认证年龄
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9") // 设置语言
                .get()
                .build();

        Call call = client4Steam.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("爬取网页失败 - 可能等待时间过长 - 将再次重试：" + url);
                urlCollector.add2Fail(url, type);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String page = response.body().string();
                System.out.println("获取页面成功 ： " + url);

                if (jobType == SpiderJob.INIT_URL_DATA) {
                    pageResolver.initOrCheckUrl(page, type);
                } else if (jobType == SpiderJob.DAILY_CHECK_URL) {
                    pageResolver.initOrCheckUrl(page, type);
                } else if (jobType == SpiderJob.INIT_APP_INFO) {
                    pageResolver.initOrCheckAppInfo(page, type, appid, url.lastIndexOf("/sub/") != -1);
                } else if (jobType == SpiderJob.DAILY_CHECK_APP_INFO) {
                    pageResolver.initOrCheckAppInfo(page, type, appid, url.lastIndexOf("/sub/") != -1);
                } else if (jobType == SpiderJob.DAILY_SPIDE_SPECIAL_PRICE) {
                    pageResolver.dailySpideSpecialPrice(url, page, appid);
                }
            }
        });
    }

    public int getSteamTotalPage(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                .addHeader("cookie", "birthtime=470678401") // 认证年龄
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9") // 设置语言
                .get()
                .build();

        Call call = client4Steam.newCall(request);
        // 这里不能使用异步获取，因为这里获得的总页数在接下来的逻辑中是有用的
        try {
            Response response = call.execute();
            int totalPage = pageResolver.resolvSteamTotalPage(response.body().string());
            return totalPage;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
