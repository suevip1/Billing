package com.pingpongx.smb.fee.common.dto;

import com.pingpongx.business.common.dto.Money;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月01日 13:54:00
 */
@Data
public class FeeItemQuery implements Serializable {
    private static final long serialVersionUID = 4104780889666200339L;
    /**
     * 部门:B2B,FLOWMORE,SMB,MPT
     */
    private String bu;

    /**
     * 地区
     */
    private String location;

    /**
     * 平台 亚马逊，虾皮
     */
    private String platform;


}
