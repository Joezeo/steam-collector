package com.joezeo.steamcollector.spider.vo;

import com.joezeo.steamcollector.common.mongo.DBVersionDocument;
import dev.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

/**
 * steam app 的 url 地址
 *
 * @author Joezeo
 * @date 2020/12/5 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(value = "steam_app_url_v1")
public class SteamAppUrl extends DBVersionDocument<ObjectId> {
    private String appid;

    private String url;

    private String type;
}
