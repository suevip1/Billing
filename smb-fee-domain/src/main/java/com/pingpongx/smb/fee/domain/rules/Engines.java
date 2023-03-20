package com.pingpongx.smb.fee.domain.rules;

import com.alibaba.fastjson2.JSON;
import com.pingpongx.smb.export.globle.Engine;
import com.pingpongx.smb.export.module.ConfiguredRangeRule;
import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.export.module.operation.RuleOr;
import com.pingpongx.smb.export.module.persistance.Or;
import com.pingpongx.smb.export.module.persistance.Range;
import com.pingpongx.smb.export.module.persistance.RangeType;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.dal.dataobject.CostNodeDo;
import com.pingpongx.smb.fee.dal.repository.CostItemRepository;
import com.pingpongx.smb.fee.dal.repository.CostNodeRepository;
import com.pingpongx.smb.fee.domain.factory.CostItemFactory;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.CostNode;
import com.pingpongx.smb.metadata.properties.JavaReflectionExtractor;
import com.pingpongx.smb.rule.routers.operatiors.NumRangeIn;
import com.pingpongx.smb.store.Codec;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Engines {
    private static final Map<String, Engine> engineOfNode = new ConcurrentHashMap<>();
    private static final Map<String, Set<String>> variables = new ConcurrentHashMap<>();
    private final CostNodeRepository nodeRepository;
    private final CostItemRepository itemRepository;
    private final CostItemFactory costItemFactory;

    private static void putEngine(CostNode node, Engine engine) {
        if (engine == null || node == null) {
            return;
        }
        engineOfNode.put(node.getCode(), engine);
    }

    public Set<String> variablesToPrepare(String costNodeCode) {
        Set<String> ret = variables.get(costNodeCode);
        if (ret == null) {
            return new HashSet<>();
        }
        //返回备份，防止被修改
        return ret.stream().collect(Collectors.toSet());
    }

    public Engine getEngine(CostNode node) {
        Engine engine = engineOfNode.get(node.getCode());
        return engine;
    }

    public Engine getEngine(String nodeCode) {
        Engine engine = engineOfNode.get(nodeCode);
        return engine;
    }

    @PostConstruct
    void init() {
        List<CostNodeDo> nodes = nodeRepository.listAllNode();
        nodes.stream().forEach(costNodeDo -> initEngine(costNodeDo));
    }

    private void initEngine(CostNodeDo costNode) {
        Engine engine = new Engine();
        Set<String> vars = new HashSet<>();
        //TODO  map extractor.
        engine.extractor = new EngineExtractorAdapt();
        List<CostItemDo> items = itemRepository.listByNodeCode(costNode.getCode());
        items.stream().map(this::tuple).forEach(t -> {
            ConfiguredRangeRule rule = new ConfiguredRangeRule();
            Range range = new Range();
            if (t._1().getStartTime()==null){
                t._1().setStartTime(0L);
            }
            if (t._1().getEndTime()==null){
                t._1().setEndTime(Range.MAX.longValue());
            }
            range.setRangeStart(t._1().getStartTime().toString());
            range.setRangeEnd(t._1().getEndTime().toString());
            range.setRangeType(RangeType.LCRC.toString());
            rule.setExpected(JSON.toJSONString(range));
            rule.setNot(false);
            rule.setAttr("billingTime");
            rule.setType(MatchContext.class.getSimpleName());
            rule.setOperation(NumRangeIn.getInstance());

            if (t._2() == null) {
                engine.put(rule, CostItemHolder.of(t._1()));
                vars.addAll(t._1().getMatchVarKeys());
            } else {
                engine.put(t._2().and(rule), CostItemHolder.of(t._1()));
                vars.addAll(t._1().getMatchVarKeys());
            }
        });
        engineOfNode.put(costNode.getCode(), engine);
        variables.put(costNode.getCode(), vars);
    }

    private RuleOr toRuleOr(String ruleStr) {
        if (ruleStr == null) {
            return null;
        }
        Or or = JSON.parseObject(ruleStr, Or.class);
        return Codec.buildRule(or);
    }

    private Tuple2<CostItem, Rule> tuple(CostItemDo costItemDo) {
        CostItem costItem = costItemFactory.load(costItemDo);
        return Tuple.of(costItem, toRuleOr(costItemDo.getMatchRule()));
    }
}

