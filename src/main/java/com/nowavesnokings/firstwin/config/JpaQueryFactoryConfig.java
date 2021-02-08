package com.nowavesnokings.firstwin.config;

import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLServer2012Templates;
import com.querydsl.sql.SQLServerTemplates;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.SQLTemplatesRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * @author ssx
 * @version V1.0
 * @className JpaQueryFactoryConfig
 * @description querydsl配置类
 * @date 2021-01-05 14:01
 * @since 1.8
 */
@Configuration
public class JpaQueryFactoryConfig {
    @ConditionalOnMissingBean
    @Bean
    public SQLTemplates sqlTemplates(DataSource dataSource) throws SQLException {
        SQLTemplatesRegistry sqlTemplatesRegistry = new SQLTemplatesRegistry();
        DatabaseMetaData metaData;
        try (Connection connection = dataSource.getConnection()) {
            metaData = connection.getMetaData();
        }
        SQLTemplates templates = sqlTemplatesRegistry.getTemplates(metaData);
        if (templates instanceof SQLServerTemplates || metaData.getDatabaseMajorVersion() > 11) {
            return new SQLServer2012Templates();
        }
        return templates;
    }

    @Bean
    public Supplier<JPASQLQuery<?>> jpaSqlFactory(EntityManager entityManager, SQLTemplates sqlTemplates) {
        return () -> new JPASQLQuery<Object>(entityManager, sqlTemplates);
    }
}
