package com.pingpongx.smb.fee.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cost_node")
public class CostNodeDo extends FeeBaseDo {
    String code;
    String displayName;
    String bizLine;
}
