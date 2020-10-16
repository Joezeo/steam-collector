package com.joezeo.steamcollector.spider;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 收集爬取过程中失败的url
 */
@SuppressWarnings("ALL")
@Component
@Slf4j
@Data
public class FailureUrlCollector {

    private static enum UrlType {
        GAME_URL("game", new ConcurrentHashMap()),
        BUNDLE_URL("bundle", new ConcurrentHashMap<>()),
        SOFTWARE_URL("software", new ConcurrentHashMap<>()),
        DLC_URL("dlc", new ConcurrentHashMap<>()),
        DEMO_URL("demo", new ConcurrentHashMap<>()),
        SOUND_URL("sound", new ConcurrentHashMap<>()),
        SUB_URL("sub", new ConcurrentHashMap<>())
        ;
        private String type;
        private Map<String, Integer> collector;

        UrlType(String type, Map<String, Integer> collector) {
            this.type = type;
            this.collector = collector;
        }

        public static UrlType typeOf(String type) {
            for (UrlType value : UrlType.values()) {
                if (value.type.equals(type)) {
                    return value;
                }
            }
            return null;
        }

        public String getType() {
            return type;
        }

        public Map<String, Integer> getCollector() {
            return collector;
        }
    }

    public void addFailure(String url, String type) {
        Map<String, Integer> collector = UrlType.typeOf(type).getCollector();
        if (collector.containsKey(url)) {
            Integer count = collector.get(url);
            if (count > 5) { // 重试超过五次就不再重试，将失败的url存入数据库中
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
