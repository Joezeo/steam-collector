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
    {
        of();
    }

    @Autowired
    protected Datastore datastore;

    protected Class<T> clazz;

    /**please use @method:of(Class<T> clazz) in the override method to init the Document class*/
    abstract protected void of();

    protected void of(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Query<T> query() {
        return datastore.createQuery(clazz);
    }

    public boolean delete(IDBDocument document) {
        return false;
    }

    public boolean up(IDBDocument document) {
        return false;
    }

    public boolean safeUp(IDBDocument document) {
        return false;
    }
}
