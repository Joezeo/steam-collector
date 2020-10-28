package com.joezeo.steamcollector.spider.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/10/23 11:33
 */
public enum UrlType {
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
