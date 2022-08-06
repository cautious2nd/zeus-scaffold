package org.scaffold.mybatis.multiDataSource.enable;

import org.scaffold.mybatis.multiDataSource.config.DataSourceFactory;
import org.scaffold.mybatis.multiDataSource.config.XATransactionManagerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：zjd
 * @date ：Created By 2022/7/6 19:35
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DataSourceFactory.class, XATransactionManagerConfig.class})
@Configuration
public @interface EnableMultiDataSource {
}
