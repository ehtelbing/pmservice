package org.building.pmservice.confing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

   /* @Bean(name = "sqlServiceDatasource")
    @ConfigurationProperties(prefix = "spring.Sqlserver")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlServiceJdbcTemplate")
    public NamedParameterJdbcTemplate sqlServiceJdbcTemplate(@Qualifier("sqlServiceDatasource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }*/


  /*  @Bean(name = "sqlServerTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("sqlServiceDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

}