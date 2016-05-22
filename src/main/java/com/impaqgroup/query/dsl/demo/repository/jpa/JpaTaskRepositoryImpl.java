package com.impaqgroup.query.dsl.demo.repository.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.model.jpa.User;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.impaqgroup.query.dsl.demo.model.jpa.QTask.task;
import static com.impaqgroup.query.dsl.demo.model.jpa.QUser.user;
import static com.mysema.query.types.expr.BooleanExpression.allOf;

@Repository
public class JpaTaskRepositoryImpl implements CustomJpaTaskRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List selectTasksJpql(Priority priority, String userName, String taskName){

        String select =
                "select t " +
                "from Task t " +
                "where t.user.name = '" + userName +"'" +
                (priority != null ? " and t.priority = ?" : "") +
                "and t.name LIKE ?";

        Query query = entityManager.createQuery(select);
        query.setParameter(0, priority);
        query.setParameter(1, taskName);
        return query.getResultList();
    }

    @Override
    public List<Task> selectTasksQueryDsl(Priority priority, String userName, String taskName){
        JPAQuery query = new JPAQuery(entityManager);

        BooleanExpression byPriority = priority != null ? task.priority.eq(priority) : null;
        BooleanExpression byUserName = task.user.name.eq(userName);
        BooleanExpression byTaskName = task.name.like(taskName);

        query.from(task).where(allOf(byPriority, byUserName, byTaskName));

        return query.list(task);
    }

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

    @Override
    public List<Task> findWithProjection(Predicate predicate, Pageable pageable){
        JPAQuery query = new JPAQuery(entityManager);

        query.from(task)
                .where(predicate)
                .orderBy(task.name.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Tuple> list = query.list(projection);
        return list.stream().map(tupleToUser).collect(Collectors.toList());
    }

    @Override
    public long delete(Predicate predicate){
        JPADeleteClause delete = new JPADeleteClause(entityManager, task);
        delete.where(predicate);
        return delete.execute();
    }

    @Override
    public long updatePriority(Priority priority, Predicate predicate){
        JPAUpdateClause update = new JPAUpdateClause(entityManager, task);
        update.set(task.priority, priority);
        update.where(predicate);
        return update.execute();
    }

}
