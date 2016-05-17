package com.impaqgroup.query.dsl.demo.repository.criteria;

import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CriteriaTaskRepository extends JpaSpecificationExecutor<Task> {
}
