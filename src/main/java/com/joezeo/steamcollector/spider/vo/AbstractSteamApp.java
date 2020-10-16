package com.joezeo.steamcollector.spider.vo;

import com.joezeo.steamcollector.common.mongo.DBVersionDocument;
import dev.morphia.annotations.Entity;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * Steam App 抽象基类，所有不同种类的 app vo 对象都应当继承此类
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/10/16 17:45
 */
@Entity(value = "steam_app_info_v1")
@Data
@SuppressWarnings("all")
public class AbstractSteamApp extends DBVersionDocument<ObjectId> {
    private static final long serialVersionUID = 2982843232445271189L;

    /**
     * steam appid
     */
    private int appid;

    private String name;

    private String imgUrl;

    private String description;

    private String releaseDate;

    private String devloper;

    private String publisher;

    private int originalPrice;

    private int finalPrice;

    private String summary;

    private long gmtCreate;

    private long gmtModify;

    private int appType;
}
