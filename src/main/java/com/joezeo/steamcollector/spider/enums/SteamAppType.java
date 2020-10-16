package com.joezeo.steamcollector.spider.enums;

import java.util.ArrayList;
import java.util.List;

public enum SteamAppType {
    /*游戏*/
    GAME(1, "game"),
    /*软件*/
    SOFTWARE(2, "software"),
    /*DLC*/
    DLC(3, "dlc"),
    /*Demo*/
    DEMO(4, "demo"),
    /*捆绑包*/
    BUNDLE(5, "bundle"),
    /*游戏原声*/
    SOUND(6, "sound"),
    /*礼包*/
    SUB(7, "sub")
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
