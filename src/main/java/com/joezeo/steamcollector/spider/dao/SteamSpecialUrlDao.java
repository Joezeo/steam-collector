package com.joezeo.steamcollector.spider.dao;

import com.joezeo.steamcollector.common.mongo.BaseDao;
import com.joezeo.steamcollector.spider.vo.SteamSpecialUrl;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

/**
 * @author Joezeo
 * @date 2020/12/6 16:22
 */
@Repository
public class SteamSpecialUrlDao extends BaseDao<ObjectId, SteamSpecialUrl> {
    @Override
    protected void classOf() {
        super.classOf(SteamSpecialUrl.class);
    }
}
