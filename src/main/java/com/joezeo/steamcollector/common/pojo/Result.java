package com.joezeo.steamcollector.common.pojo;

import com.joezeo.steamcollector.common.pojo.dto.AbstractDTO;
import lombok.Data;

import java.util.Collection;

/**
 * the final object to transfer the data from front side and server side;
 *
 * @author Joezeo
 * @date 2020/11/27 22:53
 */
@Data
public class Result<DTO extends AbstractDTO> {
    private int code;
    private String msg;
    private Collection<DTO> datas;

    public static <DTO extends AbstractDTO> Result<DTO> successOf() {
        Result<DTO> result = new Result<>();
        result.code = 200;
        result.msg = "OK";
        return result;
    }

    public static <DTO extends AbstractDTO> Result<DTO> errorOf() {
        Result<DTO> result = new Result<>();
        result.code = 500;
        result.msg = "FAILED";
        return result;
    }


    public static <DTO extends AbstractDTO> Result<DTO> successOf(Collection<DTO> datas) {
        Result<DTO> result = new Result<>();
        result.code = 200;
        result.msg = "OK";
        result.datas = datas;
        return result;
    }

    public static <DTO extends AbstractDTO> Result<DTO> resultOf(boolean success) {
        return success ? successOf() : errorOf();
    }
}
