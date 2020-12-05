package com.joezeo.steamcollector.spider.core;

import com.joezeo.steamcollector.spider.enums.UrlType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 收集爬取过程中失败的url
 */
@SuppressWarnings("ALL")
@Component
@Slf4j
@Data
public class FailureUrlCollector {

    public void addFailure(String url, String type) {
        Map<String, Integer> collector = UrlType.typeOf(type).getCollector();
        if (collector.containsKey(url)) {
            Integer count = collector.get(url);
            // 重试超过五次就不再重试，将失败的url存入数据库中
            if (count > 5) {
                removeFailure(url, type);
                writeDB(url, type);
                return;
            }
            collector.put(url, count + 1);
        } else {
            collector.put(url, 1);
        }
    }

    private void removeFailure(String url, String type) {
        Map<String, Integer> collector = UrlType.typeOf(type).getCollector();
        if (collector.containsKey(url)) {
            if ("game".equals(type)) {
                if (collector.containsKey(url)) {
                    collector.remove(url);
                }
            }
        }
    }

    private void writeDB(String url, String type) {
    }
}
