package com.pingpongx.smb.fee.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pingpongx.business.dal.core.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xuyq
 * @createTime 2022年06月28日 11:54:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("configured_var_def")
public class ConfiguredVariableDef extends MetaBaseDo {

    private static final long serialVersionUID = 8061568762858026972L;
    String path;
    String code;
    String isNum;
    String type;

    /***
     * java 类型
     */
    String sourceType;
    String nameSpace;
}
