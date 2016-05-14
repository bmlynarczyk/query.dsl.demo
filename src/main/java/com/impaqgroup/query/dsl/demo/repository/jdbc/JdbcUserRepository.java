package com.impaqgroup.query.dsl.demo.repository.jdbc;

import com.impaqgroup.query.dsl.demo.model.jdbc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class JdbcUserRepository {

    private static final String SQL_FIND_BY_NAME = "select id, name from users where name = ?";

    private JdbcOperations operations;

    @Autowired
    public JdbcUserRepository(JdbcOperations operations){
        this.operations = operations;
    }

    public User findByName(String name) {
        RowMapper<User> rowMapper = (ResultSet rs, int rowNum) -> User.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
        return operations.queryForObject(SQL_FIND_BY_NAME, new Object[]{name}, rowMapper);
    }

}
