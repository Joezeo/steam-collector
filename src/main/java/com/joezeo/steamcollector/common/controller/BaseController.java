package com.joezeo.steamcollector.common.controller;

import com.joezeo.steamcollector.common.pojo.Result;
import com.joezeo.steamcollector.common.pojo.dto.AbstractDTO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


/**
 * @author Joezeo
 * @date 2020/11/28 22:58
 */
public abstract class BaseController<DTO extends AbstractDTO> {

    /**
     * execute the client request
     *
     * @param param the dto include params
     * @param session session obj
     * @return server result
     */
    public abstract Result<DTO> execute(@RequestParam DTO param, HttpSession session);

}
