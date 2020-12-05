package com.joezeo.steamcollector.common.pojo;

import com.joezeo.steamcollector.common.pojo.dto.AbstractDTO;
import com.joezeo.steamcollector.common.pojo.po.AbstractPO;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author Joezeo
 * @date 2020/11/28 21:49
 */
@Slf4j
public abstract class AbstractPojo {
    /**
     * 自动推导转化类型，且屏蔽 unckecked cast 警告（请明白自己在干什么，否则不要使用此方法）
     * 修改：增加参数，降低了函数使用风险，当前对象和要转化的类型不一致时，会返回null；增加 PO 和 DTO 可以在此方法中自动调用其重写的转换函数自动转化
     *
     * unchecked cast : java在父类转具体子类时会发出相应的警告，这是无可避免的，只用通过suppress来解决
     *
     * @param <T> 需要转换成为的类型
     * @return 转化后的类型
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> cast(Class<T> clazz) {
        Object me = this;
        if (this instanceof AbstractDTO && AbstractPO.class.equals(clazz.getSuperclass())) {
            me = ((AbstractDTO) this).toPo();
        }
        if (this instanceof AbstractPO && AbstractDTO.class.equals(clazz.getSuperclass())) {
            me = ((AbstractPO) this).toDTO();
        }
        if (!me.getClass().equals(clazz)) {
            log.error("自动cast转化类型失败，返回null ： this->class={}, cast->class={}", this.getClass().getName(), clazz.getName());
            return Optional.empty();
        }
        return Optional.of((T) me);
    }
}
