package com.pingpongx.smb.fee.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pingpongx.business.dal.core.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 11:54:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@TableName("repeat")
public class RepeatDo extends FeeBaseDo {

    private static final long serialVersionUID = 8061568762858026972L;

    private String scope;

    private String repeatKey;


}
