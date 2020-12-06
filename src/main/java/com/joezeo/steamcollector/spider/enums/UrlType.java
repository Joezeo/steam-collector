package com.joezeo.steamcollector.spider.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/10/23 11:33
 */
public enum UrlType {
    /**
     * steam url type
     * type: 游戏
      */
    GAME_URL(
            "game",
            "https://store.steampowered.com/search/?category1=998&ignore_preferences=1&page=",
            new ConcurrentHashMap()
    ),

    /**
     * steam url type
     * type: 捆绑包
     */
    BUNDLE_URL(
            "bundle",
            "https://store.steampowered.com/search/?category1=996&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    ),

    /**
     * steam url type
     * type: 软件
     */
    SOFTWARE_URL(
            "software",
            "https://store.steampowered.com/search/?category1=994&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    ),

    /**
     * steam url type
     * type: DLC
     */
    DLC_URL(
            "dlc",
            "https://store.steampowered.com/search/?category1=21&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    ),

    /**
     * steam url type
     * type: 试玩游戏
     */
    DEMO_URL(
            "demo",
            "https://store.steampowered.com/search/?category1=10&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    ),

    /**
     * steam url type
     * type: 原声带
     */
    SOUND_URL(
            "sound",
            "https://store.steampowered.com/search/?category1=990&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    ),

    /**
     * steam url type
     * type: 视频
     */
    VIDEO_URL(
            "video",
            "https://store.steampowered.com/search/?category1=992&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    ),

    /**
     * steam url type
     * type: 模组
     */
    MODULE_URL(
            "module",
            "https://store.steampowered.com/search/?category1=997&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    ),

    /**
     * 特惠商品
     *
     * 注意：当天特惠商品的url应当和其他app分开存储
     */
    SPECIAL_URL(
            "special",
            "https://store.steampowered.com/search/?filter=topsellers&specials=1&ignore_preferences=1&page=",
            new ConcurrentHashMap<>()
    )
    ;

    /**
     * steam app 类型
     */
    private final String type;

    /**
     * 通过steam搜索页面爬取所有软件的url
     * rootPage 及爬取的根网页
     */
    private final String rootPage;

    /**
     * 爬取 app 信息失败时的失败 url 收集器
     */
    private final Map<String, Integer> collector;

    UrlType(String type, String rootPage, Map<String, Integer> collector) {
        this.type = type;
        this.rootPage = rootPage;
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

    public String getRootPage() {
        return rootPage;
    }

    public Map<String, Integer> getCollector() {
        return collector;
    }
}
