package com.joezeo.steamcollector.common.mongo;

import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;

/**
 * 所有mongo集合对应的vo对象都应该继承此类
 * 泛型K表示此集合的_id类型
 *
 * 如需集合提供乐观锁，请继承类com.joezeo.steamcollector.common.mongo.DBOptimisticDocument
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/9/2 18:32
 */
public abstract class DBDocument<K> implements Serializable {
    @Id
    private K id;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }
}
