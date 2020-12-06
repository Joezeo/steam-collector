package com.joezeo.steamcollector.spider.core.resolver;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Joezeo
 * @date 2020/12/6 16:24
 */
@Component
public class AppInfoResolver extends AbstractPageResolver{
    @Override
    public int resolveNumber(String page, Map<String, Object> params) {
        return 0;
    }

    @Override
    public void resolve(String page, Map<String, Object> params) {

    }
}
