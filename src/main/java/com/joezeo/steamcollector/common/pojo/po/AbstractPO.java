package com.joezeo.steamcollector.common.pojo.po;


import com.joezeo.steamcollector.common.pojo.AbstractPojo;
import com.joezeo.steamcollector.common.pojo.dto.AbstractDTO;

/**
 * the object class to database
 *
 * @author Joezeo
 * @date 2020/11/28 16:26
 */
public abstract class AbstractPO extends AbstractPojo {
    /**
     * transfer the po to dto
     *
     * @return DTO对象
     */
    public abstract AbstractDTO toDTO();
}
