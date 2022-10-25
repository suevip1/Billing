package com.pingpongx.smb.fee.common.constants;

import lombok.Getter;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月01日 15:43:00
 */
public enum FeeConfigTypeEnum {
    ALl(0, "全局"),
    USER(1, "个人");


    @Getter
    private int type;

    @Getter
    private String desc;


    FeeConfigTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
