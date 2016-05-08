package com.impaqgroup.query.dsl.demo.repository;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jdbc.Task;
import com.impaqgroup.query.dsl.demo.model.jdbc.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class JdbcTaskRepository {

    private static final String SQL_INSERT_TASK = "insert into task (id, name, user_id, priority) values (?, ?, ?, ?)";
    private static final String SQL_FIND_ALL = "select id, name, user_id, priority from task";

    private JdbcOperations operations;

    public JdbcTaskRepository(JdbcOperations operations){
        this.operations = operations;
    }

    public void save(Task task) {
        operations.update(SQL_INSERT_TASK,
                task.getId(),
                task.getName(),
                task.getAssignee().getId(),
                task.getPriority().name()
        );
    }

    public List<Task> findAll() {
        RowMapper<Task> rowMapper = (ResultSet rs, int rowNum) -> Task.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .assignee(User.builder().id(rs.getInt("user_id")).build())
                .priority(Priority.valueOf(rs.getString("priority")))
                .build();
        return operations.query(SQL_FIND_ALL, rowMapper);
    }

}
