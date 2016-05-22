package com.impaqgroup.query.dsl.demo.repository.jpa;

import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface JpaTaskRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task>, QueryDslPredicateExecutor<Task>, CustomJpaTaskRepository {

    @Query(value = "select t from Task t where t.user.name = ?")
    List<Task> selectTasksByUserName(String userName);

    @Query(value = "select * from tasks t join users u on t.user_id = u.id where u.name = ?", nativeQuery = true)
    List<Task> selectTasksByUserNameNative(String userName);

//    @Query(value = "select t from Task t where t.user.Name = ?")
//    List<Task> selectTasksByUserNameWrongQuery(String userName);
//
//    List<Task> findByUserNamee(String userNamee);

    List<Task> findByUserName(Integer userName);

}
