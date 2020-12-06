package com.joezeo.steamcollector.spider.core.resolver;

import java.util.Map;

/**
 * @author Joezeo
 * @date 2020/12/6 0:13
 */
public abstract class AbstractPageResolver {
    /**
     * 解析网页，获取一个数值
     *
     * @param page 网页html结构字符串
     * @param params 可能需要的参数
     * @return
     */
    public abstract int resolveNumber(String page, Map<String, Object> params);

    /**
     * 无返回值解析一个网页
     *
     * @param page 网页html结构字符串
     * @param params 可能需要的参数
     */
    public abstract void resolve(String page, Map<String, Object> params);
}
