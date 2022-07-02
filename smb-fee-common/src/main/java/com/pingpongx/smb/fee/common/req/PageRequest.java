package com.pingpongx.smb.fee.common.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangcheng
 * @Description 分页查询request
 * @createTime 2022年06月29日 17:08:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest implements Serializable {

    @Builder.Default
    public Integer pageNo = 1;

    @Builder.Default
    public Integer pageSize = 20;

}
