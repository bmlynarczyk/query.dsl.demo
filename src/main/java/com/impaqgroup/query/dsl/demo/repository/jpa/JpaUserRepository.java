package com.impaqgroup.query.dsl.demo.repository.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    @Query(value = "select t.user from Task t where t.priority = ?")
    List<User> selectTasksByTaskPriority(Priority priority);

}
