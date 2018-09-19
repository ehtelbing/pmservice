package org.building.pmservice.confing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig implements TransactionManagementConfigurer {
    @Resource
    private PlatformTransactionManager transactionManager;

    @Primary
    @Bean(name = "oracleDatasource")
    @ConfigurationProperties(prefix = "spring.oracle")
    public DataSource oracleDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleJdbcTemplate")
    public NamedParameterJdbcTemplate oracleJdbcTemplate(@Qualifier("oracleDatasource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "sqlServiceDatasource")
    @ConfigurationProperties(prefix = "spring.Sqlserver")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlServiceJdbcTemplate")
    public NamedParameterJdbcTemplate sqlServiceJdbcTemplate(@Qualifier("sqlServiceDatasource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracleDatasource") DataSource dataSource) {
        DataSourceTransactionManager transactionManagertemp = new DataSourceTransactionManager(dataSource);
        transactionManager = transactionManagertemp;
        return transactionManagertemp;
    }

    @Bean(name = "sqlServerTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("sqlServiceDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // 实现接口 TransactionManagementConfigurer 方法，其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager;
    }
}