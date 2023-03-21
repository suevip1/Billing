package com.pingpongx.smb.fee.domain.factory;

import com.alibaba.fastjson2.JSON;
import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.export.module.persistance.RuleDto;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.rule.MatchRuleDto;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.domain.enums.CalculateMode;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.enums.Direction;
import com.pingpongx.smb.fee.domain.factory.expr.ExprFactory;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.store.Codec;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CostItemFactory {
    private  final  ExprFactory exprFactory;
    public CostItem load(CostItemDo costItemDo){
        //TODO
        CostItem costItem = new CostItem();
        costItem.setCostNodeCode(costItemDo.getCostNodeCode());
        if (!StringUtils.isEmpty(costItemDo.getCalculateExpress())){
            Expr expr = exprFactory.load(JSON.parseObject(costItemDo.getCalculateExpress(), ExprDto.class));
            costItem.setCalculateExpress(expr);
        }
        costItem.setCode(costItemDo.getCode());
        if (!StringUtils.isEmpty(costItemDo.getMatchRule())){
            MatchRuleDto matchRuleDto = JSON.parseObject(costItemDo.getMatchRule(), MatchRuleDto.class);
            Rule rule = Codec.buildRule(matchRuleDto.toEngineRule());
            costItem.setMatchRule(rule);
        }
        if (!StringUtils.isEmpty(costItemDo.getMatchVarKeys())){
            List<String> keys = JSON.parseArray(costItemDo.getMatchVarKeys(), String.class);
            costItem.setMatchVarKeys(keys);
        }
        if (!StringUtils.isEmpty(costItemDo.getCalculateVarKeys())){
            List<String> keys = JSON.parseArray(costItemDo.getCalculateVarKeys(), String.class);
            costItem.setCalculateVarKeys(keys);
        }
        if (!StringUtils.isEmpty(costItemDo.getMode())){
            CalculateMode mode = CalculateMode.valueOf(costItemDo.getMode());
            costItem.setMode(mode);
        }
        costItem.setCollectionCode(costItemDo.getCollectionCode());
        costItem.setCurrencyType(CurrencyType.valueOf(costItemDo.getCurrencyType()));
        costItem.setPriority(costItemDo.getPriority());
        if (!StringUtils.isEmpty(costItemDo.getInOrOut())){
            Direction direction = Direction.valueOf(costItemDo.getInOrOut());
            costItem.setInOrOut(direction);
        }
        costItem.setStartTime(costItemDo.getStartTime());
        costItem.setEndTime(costItemDo.getEndTime());
        costItem.setDisplayName(costItemDo.getDisplayName());
        costItem.setContractCode(costItemDo.getContractCode());
        costItem.setCostNodeCode(costItemDo.getCostNodeCode());
        return costItem;
    }
}
