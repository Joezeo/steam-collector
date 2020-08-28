package com.joezeo.steamcollector.common.mongo;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mongoDB连接对象，负责建立mongo连接
 * 由spring托管，单例对象
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/28 16:48
 */
@Component
public class MongoDB {
    @Autowired
    private Datastore datastore;
}
