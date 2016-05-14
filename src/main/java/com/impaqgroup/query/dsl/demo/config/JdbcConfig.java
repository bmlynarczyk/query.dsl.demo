package com.impaqgroup.query.dsl.demo.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = {
        "com.impaqgroup.query.dsl.demo.repository.jdbc"
})
@Import(value = {
        DataSourceAutoConfiguration.class,
        LiquibaseAutoConfiguration.class
})
public class JdbcConfig {
}
