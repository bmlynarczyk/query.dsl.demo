package com.impaqgroup.query.dsl.demo.model.jdbc;

import com.impaqgroup.query.dsl.demo.model.Priority;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Task {

    private final Integer id;
    private final String name;
    private final User assignee;
    private final Priority priority;

}
