package com.joezeo.steamcollector.common.mongo;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
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
    public abstract boolean add(DBDocument<K> document);

    /** delete */
    public abstract boolean delete(K id);

    /** update */
    public abstract boolean update(DBDocument<K> document);

    protected UpdateOperations<T> ops() {
        return datastore.createUpdateOperations(clazz);
    }

    protected boolean up(UpdateOperations<T> ops) {
        return false;
    }

    protected boolean safeUp(UpdateOperations<T> ops) {
        return false;
    }

    /** query */
    public abstract DBDocument<K> query(K id);

    protected Query<T> query() {
        return datastore.find(clazz);
    }
}
