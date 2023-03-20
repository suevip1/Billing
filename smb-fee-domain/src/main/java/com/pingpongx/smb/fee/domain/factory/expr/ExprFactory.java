package com.pingpongx.smb.fee.domain.factory.expr;

import com.alibaba.fastjson.JSON;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExprFactory {
    private final Map<String,IExprFactory> factoryMap;

    public  Expr load(ExprDto exprDto){
        String type = exprDto.getType();
        IExprFactory factory = factoryMap.get(type);
        if (factory == null){
            throw new RuntimeException("not supported type. "+ JSON.toJSONString(exprDto));
        }
        return factory.load(exprDto);
    }
}
