package com.pingpongx.smb.fee.domain.rules;

import com.pingpongx.smb.export.module.PipelineContext;
import com.pingpongx.smb.export.spi.FunctionalHandler;
import com.pingpongx.smb.export.spi.RuleHandler;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.domain.module.CostItem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CostItemHolder implements FunctionalHandler<Object,CostItem> {
    private CostItem costItem;

    @Override
    public List<String> tags() {
        return Stream.of("CostItem Match").collect(Collectors.toList());
    }

    @Override
    public String getIdentify() {
        return costItem.getCode();
    }

    public CostItem getCostItem() {
        return costItem;
    }
    public static CostItemHolder of(CostItem costItem){
        CostItemHolder holder = new CostItemHolder();
        holder.costItem = costItem;
        return holder;
    }

    @Override
    public CostItem applyData(Object data) {
        return getCostItem();
    }
}
