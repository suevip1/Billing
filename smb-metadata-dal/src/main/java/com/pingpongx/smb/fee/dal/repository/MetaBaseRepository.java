package com.pingpongx.smb.fee.dal.repository;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pingpongx.business.dal.core.DefaultMapper;
import com.pingpongx.business.dal.core.SingleKey;
import com.pingpongx.smb.fee.dal.dataobject.MetaBaseDo;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MetaBaseRepository<M extends DefaultMapper<T>, T extends MetaBaseDo> extends ServiceImpl<M, T> {
    /**
     * 根据 clientId 查询
     *
     * @param clientId clientId
     * @return ~
     */
    public T getOneByClientId(String clientId) {
        return byClientId(clientId)
                .one();
    }

    /**
     * 根据 clientId 查询
     *
     * @param clientId clientId
     * @return ~
     */
    public List<T> listByClientId(String clientId) {
        return byClientId(clientId)
                .list();
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                /*
                 * 更新成功直接返回，失败执行插入逻辑
                 */
                return updateBySingleKey(entity) || save(entity);
            } else {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    /**
     * 可以通过重写此方法来改变 insert or update 时 所使用的判断条件
     * ( 此方法返回 true , 将不会执行 insert, 否则将会执行 insert)
     *
     * @param entity 对象
     * @return 有记录成功被更新 返回 true , 否则返回 false
     */
    public boolean updateBySingleKey(T entity) {
        Map<String, Object> where = new HashMap<>();
        ReflectionUtils.doWithFields(entity.getClass(),
                // 增加 where 条件
                field -> where.put(field.getName(), field.get(entity)),
                // 增加了 @SingleKey 注解的字段
                field -> {
                    ReflectionUtils.makeAccessible(field);
                    return field.isAnnotationPresent(SingleKey.class);
                });
        return where.isEmpty() ?
                super.updateById(entity) :
                update().allEq(where).update(entity);
    }

    // ---------------------------------------------------------------------------------

    private QueryChainWrapper<T> byClientId(String clientId) {
        return query()
                .eq("clientId", clientId);
    }
}
