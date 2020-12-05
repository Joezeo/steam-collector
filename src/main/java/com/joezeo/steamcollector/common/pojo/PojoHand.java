package com.joezeo.steamcollector.common.pojo;

import com.joezeo.steamcollector.common.pojo.dto.AbstractDTO;
import com.joezeo.steamcollector.common.pojo.po.AbstractPO;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里的转化函数其实还是存在代码设计问题，程序员自行保证这里传入的泛型类型，是PO/DTO中重写的转换函数转化的实际类型，否则cast后会返回Null
 *
 * @author Joezeo
 * @date 2020/11/28 20:51
 */
public class PojoHand {

    public static <DTO extends AbstractDTO> List<DTO> asDtoList(List<? extends AbstractPO> pos, Class<DTO> clazz) {
        List<DTO> dtos = new ArrayList<>();
        pos.forEach(po -> po.cast(clazz).ifPresent(dtos::add));
        return dtos;
    }

    public static <PO extends AbstractPO> List<PO> asPoList(List<? extends AbstractDTO> dtos, Class<PO> clazz) {
        List<PO> pos = new ArrayList<>();
        dtos.forEach(dto -> dto.cast(clazz).ifPresent(pos::add));
        return pos;
    }
}
