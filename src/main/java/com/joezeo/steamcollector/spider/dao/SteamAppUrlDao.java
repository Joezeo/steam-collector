package com.joezeo.steamcollector.spider.dao;

import com.joezeo.steamcollector.common.mongo.BaseDao;
import com.joezeo.steamcollector.spider.vo.SteamAppUrl;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joezeo
 * @date 2020/12/5 16:00
 */
@Repository
public class SteamAppUrlDao extends BaseDao<ObjectId, SteamAppUrl> {
    @Override
    protected void classOf() {
        super.classOf(SteamAppUrl.class);
    }

    public List<SteamAppUrl> listOfAppidAndType(String appid, String type) {
        Query<SteamAppUrl> query = query();
        query.filter(
                Filters.eq("appid", appid),
                Filters.eq("type", type)
        );
        return query.iterator().toList();
    }

    public SteamAppUrl ofAppidAndType(String appid, String type) {
        Query<SteamAppUrl> query = query();
        query.filter(
                Filters.eq("appid", appid),
                Filters.eq("type", type)
        );
        return query.first();
    }
}
