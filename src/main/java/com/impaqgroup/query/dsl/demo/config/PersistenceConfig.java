package com.impaqgroup.query.dsl.demo.config;

import com.impaqgroup.query.dsl.demo.repository.JdbcTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Bean
    public JdbcTaskRepository jdbcTaskRepository(DataSource dataSource){
        return new JdbcTaskRepository(new JdbcTemplate(dataSource));
    }

}
