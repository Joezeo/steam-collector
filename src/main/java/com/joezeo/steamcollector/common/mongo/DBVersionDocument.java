package com.joezeo.steamcollector.common.mongo;


import dev.morphia.annotations.Version;
import lombok.Data;

/**
 * 提供了乐观锁机制的文档vo对象基类
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/9/5 17:39
 */
@Data
public abstract class DBVersionDocument<K> extends DBDocument<K> {
    @Version
    private long version;
}
