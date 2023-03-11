package com.pingpongx.smb.fee.domain.rules;

import com.pingpongx.smb.export.module.PipelineContext;
import com.pingpongx.smb.export.spi.RuleHandler;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.domain.module.CostItem;

public class CostItemHolder implements RuleHandler<CostItem> {
    private CostItem costItem;
    @Override
    public void handleMatchedData(CostItem data, PipelineContext context) {

    }

    @Override
    public String scene() {
        return "CostItem Match";
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
}
