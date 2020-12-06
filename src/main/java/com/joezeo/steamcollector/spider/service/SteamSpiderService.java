package com.joezeo.steamcollector.spider.service;

import com.joezeo.steamcollector.spider.core.PageGetter;
import com.joezeo.steamcollector.spider.enums.SpiderJob;
import com.joezeo.steamcollector.spider.enums.UrlType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author Joezeo
 * @date 2020/12/5 16:37
 */
@Slf4j
@RestController
@AllArgsConstructor
public class SteamSpiderService {

    private final PageGetter pageGetter;

    @GetMapping("test")
    public void initUrl() {
        Arrays.stream(UrlType.values()).forEach(urltype -> {
            int totalPage = 0;
            while (totalPage == 0) {
                totalPage = pageGetter.getSteamTotalPage(urltype.getRootPage() + 1);
            }
            for (int i = 1; i <= totalPage; i++) {
                pageGetter.spiderUrlAsyn(urltype.getRootPage() + i, urltype.getType(), null, SpiderJob.INIT_URL_DATA);
            }
        });
    }
}
