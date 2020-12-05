package com.joezeo.steamcollector.common.mongo;

import com.joezeo.steamcollector.common.pojo.AbstractPojo;
import dev.morphia.annotations.Id;
import lombok.Data;

import java.io.Serializable;

/**
 * 所有mongo集合对应的vo对象都应该继承此类
 * 泛型K表示此集合的_id类型
 *
 * 如需集合提供乐观锁，请继承类com.joezeo.steamcollector.common.mongo.DBVersionDocument
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/9/2 18:32
 */
@Data
public abstract class DBDocument<K> extends AbstractPojo implements Serializable{
    @Id
    private K id;
}
