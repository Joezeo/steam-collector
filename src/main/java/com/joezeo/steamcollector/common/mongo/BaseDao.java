package com.joezeo.steamcollector.common.mongo;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filter;
import dev.morphia.query.experimental.filters.Filters;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Dao类对象的基类
 * 所有dao都应继承此类
 *
 * 泛型：
 *      K：表示集合_id属性的类型
 *      T：表示数据库集合对应的vo对象类
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/9/2 18:32
 */
public abstract class BaseDao<K,T extends DBDocument<K>> {
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

    /** add */
    public boolean add(DBDocument<K> document) {
        try {
            this.datastore.save(document);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** delete */
    public boolean delete(K id) {
        try {
            Query<T> query = query();
            Filter filter = Filters.eq("_id", id);
            query.filter(filter);
            query.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** update */
    public abstract boolean update(DBDocument<K> document);

    /** query */
    public abstract DBDocument<K> query(K id);

    protected Query<T> query() {
        return datastore.find(clazz);
    }
}
