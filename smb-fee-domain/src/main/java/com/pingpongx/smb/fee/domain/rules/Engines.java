package com.pingpongx.smb.fee.domain.rules;

import com.alibaba.fastjson2.JSON;
import com.pingpongx.smb.export.globle.Engine;
import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.export.module.operation.RuleOr;
import com.pingpongx.smb.export.module.persistance.Or;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.dal.dataobject.CostNodeDo;
import com.pingpongx.smb.fee.dal.repository.CostItemRepository;
import com.pingpongx.smb.fee.dal.repository.CostNodeRepository;
import com.pingpongx.smb.fee.domain.factory.CostItemFactory;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.CostNode;
import com.pingpongx.smb.store.Codec;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class Engines {
    private final CostNodeRepository nodeRepository;
    private final CostItemRepository itemRepository;
    private static volatile Map<String, Engine> engineOfNode = new ConcurrentHashMap<>();

    private static void putEngine(CostNode node, Engine engine) {
        if (engine == null || node == null) {
            return;
        }
        engineOfNode.put(node.getCode(), engine);
    }

    private static Engine getEngine(CostNode node) {
        Engine engine = engineOfNode.get(node.getCode());
        return engine;
    }

    @PostConstruct
    void init(){
        List<CostNodeDo> nodes = nodeRepository.listAllNode();
        nodes.stream().forEach(costNodeDo -> initEngine(costNodeDo));
    }

    private Engine initEngine(CostNodeDo costNode){
        Engine engine = new Engine();
        List<CostItemDo> items = itemRepository.listByNodeCode(costNode.getCode());
        items.stream().map(this::tuple).forEach(t -> engine.put(t._2(),CostItemHolder.of(t._1())));
        return engine;
    }

    private RuleOr toRuleOr(String ruleStr){
        Or or = JSON.parseObject(ruleStr,Or.class);
        return Codec.buildRule(or);
    }

    private Tuple2<CostItem,Rule> tuple(CostItemDo costItemDo){
        CostItem costItem = CostItemFactory.load(costItemDo);
        return Tuple.of(costItem,toRuleOr(costItemDo.getMatchRule()));
    }
}

