package com.joezeo.steamcollector.common.pojo.dto;


import com.joezeo.steamcollector.common.pojo.AbstractPojo;
import com.joezeo.steamcollector.common.pojo.po.AbstractPO;

/**
 * data trasfer object (DTO) super class
 * dto is the object that communicate with front side
 *
 * its function is :
 * server -> client : transfer the data
 * client -> server : transfer the param
 *
 * @author Joezeo
 * @date 2020/11/27 22:54
 */
public abstract class AbstractDTO extends AbstractPojo {
    /**
     * transfer dto to po
     *
     * @return po
     */
    public abstract AbstractPO toPo();
}
