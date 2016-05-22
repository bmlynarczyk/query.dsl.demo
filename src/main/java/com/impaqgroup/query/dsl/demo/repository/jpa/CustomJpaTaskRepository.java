package com.impaqgroup.query.dsl.demo.repository.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

public interface CustomJpaTaskRepository {

    List selectTasksJpql(Priority priority, String userName, String taskName);

    List<Task> selectTasksQueryDsl(Priority priority, String userName, String taskName);

    List<Task> findWithProjection(Predicate predicate);

    List<Task> findWithProjection(Predicate predicate, Pageable pageable);

    @Transactional
    long delete(Predicate predicate);

    @Transactional
    long updatePriority(Priority priority, Predicate predicate);
}
