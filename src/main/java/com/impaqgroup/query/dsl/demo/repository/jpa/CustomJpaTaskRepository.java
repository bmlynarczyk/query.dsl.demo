package com.impaqgroup.query.dsl.demo.repository.jpa;

import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.mysema.query.types.Predicate;

import java.util.List;

public interface CustomJpaTaskRepository {

    List<Task> findWithProjection(Predicate predicate);

}
