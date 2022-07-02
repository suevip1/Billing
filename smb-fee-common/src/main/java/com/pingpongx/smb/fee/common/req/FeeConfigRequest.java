package com.pingpongx.smb.fee.common.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月29日 17:06:00
 */
@Data
public class FeeConfigRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID = 5235809923175998978L;

    private String business;

    private String bu;

    private Integer configType;


}
