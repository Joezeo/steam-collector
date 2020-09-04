package com.joezeo.steamcollector.common.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Dao类对象的基类
 * 所有dao都应继承此类
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/9/2 18:32
 */
public abstract class BaseDao<T extends IDBDocument> {

    @Autowired
    protected Datastore datastore;

    protected Class<T> clazz;

    public BaseDao<T> of (Class<T> clazz) {
        this.clazz = clazz;
        return this;
    }

    public Query<T> query() {
        return datastore.createQuery(clazz);
    }
}
