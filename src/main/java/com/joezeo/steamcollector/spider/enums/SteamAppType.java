package com.joezeo.steamcollector.spider.enums;

import java.util.ArrayList;
import java.util.List;

public enum SteamAppType {
    GAME(1, "game"),
    SOFTWARE(2, "software"),
    DLC(3, "dlc"),
    DEMO(4, "demo"),
    BUNDLE(5, "bundle"),
    SOUND(6, "sound"),
    SUB(7, "sub") // 礼包
    ;

    private Integer index;
    private String type;

    SteamAppType(Integer index, String type) {
        this.index = index;
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public static String typeOf(Integer index) {
        for (SteamAppType typeEnum : SteamAppType.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum.getType();
            }
        }
        return null;
    }

    public static List<String> listType(){
        List<String> list = new ArrayList<>();
        for (SteamAppType value : SteamAppType.values()) {
            list.add(value.getType());
        }
        return list;
    }
}
