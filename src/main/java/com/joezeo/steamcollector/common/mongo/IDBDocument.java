package com.joezeo.steamcollector.common.mongo;

import java.io.Serializable;

/**
 * 所有mongo集合对应的vo对象都应该继承此类
 * 泛型K表示此集合的_id类型
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/9/2 18:32
 */
public interface IDBDocument<K> extends Serializable {

}
