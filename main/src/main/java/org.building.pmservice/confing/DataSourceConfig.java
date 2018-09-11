package org.building.pmservice.confing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "oracleDatasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource oracleDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleJdbcTemplate")
    public NamedParameterJdbcTemplate oracleJdbcTemplate(@Qualifier("oracleDatasource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "secondDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondJdbcTemplate")
    public NamedParameterJdbcTemplate secondJdbcTemplate(@Qualifier("secondDatasource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
