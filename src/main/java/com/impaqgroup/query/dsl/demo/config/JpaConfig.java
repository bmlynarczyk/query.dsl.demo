package com.impaqgroup.query.dsl.demo.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.impaqgroup.query.dsl.demo.repository.jpa"
})
@EntityScan(basePackages = {
		"com.impaqgroup.query.dsl.demo.model.jpa"
})
@Import(value = {
		DataSourceAutoConfiguration.class,
		LiquibaseAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
})
public class JpaConfig {
}
