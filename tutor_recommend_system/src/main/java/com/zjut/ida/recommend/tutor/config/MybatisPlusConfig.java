package com.zjut.ida.recommend.tutor.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * page helper分页插件
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    /**
     * 配置自动填充
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }

            /**
             * 严格模式填充策略：默认有值不覆盖,如果提供的值为null也不填充
             *
             * 更改：
             * 对于 createTime 和 updateTime 字段，完全由自动填充策略控制
             */
            @Override
            public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
                if (metaObject.getValue(fieldName) == null
                        || "createTime".equals(fieldName)
                        || "updateTime".equals(fieldName)) {
                    Object obj = fieldVal.get();
                    if (Objects.nonNull(obj)) {
                        metaObject.setValue(fieldName, obj);
                    }
                }
                return this;
            }
        };
    }
}
