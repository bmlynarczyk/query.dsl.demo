package com.impaqgroup.query.dsl.demo.repository.jdbc;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jdbc.Task;
import com.impaqgroup.query.dsl.demo.model.jdbc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class JdbcTaskRepository {

    private static final String SQL_INSERT_TASK = "insert into tasks (name, user_id, priority) values (?, ?, ?)";
    private static final String SQL_FIND_ALL = "select id, name, user_id, priority from tasks";

    private JdbcOperations operations;

    @Autowired
    public JdbcTaskRepository(JdbcOperations operations){
        this.operations = operations;
    }

    public void save(Task task) {
        operations.update(SQL_INSERT_TASK,
                task.getName(),
                task.getUser().getId(),
                task.getPriority().name()
        );
    }

    public List<Task> findAll() {
        RowMapper<Task> rowMapper = (ResultSet rs, int rowNum) -> Task.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .user(User.builder().id(rs.getInt("user_id")).build())
                .priority(Priority.valueOf(rs.getString("priority")))
                .build();
        return operations.query(SQL_FIND_ALL, rowMapper);
    }

}
