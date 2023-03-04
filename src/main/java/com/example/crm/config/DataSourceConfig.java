package com.example.crm.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Программная конфигурация для БД
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/scholarbd");
        dataSourceBuilder.username("axothy");
        dataSourceBuilder.password("springpro5");
        return dataSourceBuilder.build();
    }
}
