package com.impaqgroup.query.dsl.demo.model.jdbc;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    private final Integer id;
    private final String name;

}
