package com.joezeo.steamcollector.common.mongo;


import dev.morphia.annotations.Version;

/**
 * 提供了乐观锁机制的文档vo对象基类
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/9/5 17:39
 */
public abstract class DBVersionDocument<K> extends DBDocument<K> {
    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
