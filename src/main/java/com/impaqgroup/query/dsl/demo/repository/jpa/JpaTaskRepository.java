package com.impaqgroup.query.dsl.demo.repository.jpa;

import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaTaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "select t from Task t where t.user.name = ?")
    List<Task> selectTasksByUserName(String userName);

    @Query(value = "select * from tasks t join users u on t.user_id = u.id where u.name = ?", nativeQuery = true)
    List<Task> selectTasksByUserNameNative(String userName);

}
