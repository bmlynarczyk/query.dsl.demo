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

    private JdbcOperations operations;

    @Autowired
    public JdbcTaskRepository(JdbcOperations operations){
        this.operations = operations;
    }

    public void save(Task task) {
        operations.update("insert into tasks (name, user_id, priority) values (?, ?, ?)",
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
        return operations.query("select id, name, user_id, priority from tasks", rowMapper);
    }

    public int deleteAll() {
        return operations.update("delete from tasks");
    }

}
