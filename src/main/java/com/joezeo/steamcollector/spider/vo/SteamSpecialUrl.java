package com.joezeo.steamcollector.spider.vo;

import com.joezeo.steamcollector.common.mongo.DBDocument;
import dev.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

/**
 * @author Joezeo
 * @date 2020/12/5 23:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(value = "steam_special_url_v1")
public class SteamSpecialUrl extends DBDocument<ObjectId> {
    private String appid;

    private String url;

    private String type;
}
