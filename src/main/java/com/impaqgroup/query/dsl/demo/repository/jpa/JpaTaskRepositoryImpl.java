package com.impaqgroup.query.dsl.demo.repository.jpa;

import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.model.jpa.User;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Predicate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.impaqgroup.query.dsl.demo.model.jpa.QTask.task;
import static com.impaqgroup.query.dsl.demo.model.jpa.QUser.user;

@Repository
public class JpaTaskRepositoryImpl implements CustomJpaTaskRepository {

    @PersistenceContext
    EntityManager entityManager;

    private final Function<Tuple, Task> tupleToUser = tuple -> Task.builder()
            .id(tuple.get(task.id))
            .name(tuple.get(task.name))
            .user(User.builder()
                    .id(tuple.get(user.id))
                    .build())
            .build();

    Expression<?>[] projection = {
            task.id,
            task.name,
            task.user.id,
            task.priority
    };

    @Override
    public List<Task> findWithProjection(Predicate predicate){
        JPAQuery query = new JPAQuery(entityManager);
        List<Tuple> list = query.from(task).where(predicate).list(projection);
        return list.stream().map(tupleToUser).collect(Collectors.toList());
    }

}
