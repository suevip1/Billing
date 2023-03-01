package com.pingpongx.smb.metadata.feign;

import com.pingpongx.smb.metadata.cmd.SaveOrUpdate;
import com.pingpongx.smb.metadata.constant.Const;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
import com.pingpongx.smb.metadata.qry.GetByCode;
import com.pingpongx.smb.metadata.qry.ListAll;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * @author xuyq
 * @Description 费用定义，定义获取
 * @createTime 2022年07月02日 10:14:00
 */
@FeignClient(value = Const.APP_NAME, path = VarDefServiceFeign.BASE_PATH)
public interface VarDefServiceFeign {

    String BASE_PATH = Const.RPC_PATH_V1 + "/smb-metadata/varDefinition";

    @PostMapping("/saveOrUpdate")
    VariableDefDto saveOrUpdate(@RequestBody SaveOrUpdate request);

    @PostMapping("/saveOrUpdate")
    List<VariableDefDto> getByCode(@RequestBody GetByCode request);

    @PostMapping("/listAll")
    List<VariableDefDto> getByCode(@RequestBody ListAll request);

}
