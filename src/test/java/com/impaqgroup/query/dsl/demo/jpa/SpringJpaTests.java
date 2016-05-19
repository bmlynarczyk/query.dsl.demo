package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.config.JpaConfig;
import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.*;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaTaskRepository;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaUserRepository;
import com.mysema.query.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.Join;
import java.util.List;

import static com.google.common.collect.Iterables.size;
import static com.impaqgroup.query.dsl.demo.model.jpa.QTask.task;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaConfig.class)
public class SpringJpaTests {

	@Autowired
	private JpaTaskRepository jpaTaskRepository;

	@Autowired
	private JpaUserRepository jpaUserRepository;

	@Before
	public void setUp(){
		jpaTaskRepository.deleteAll();
	}

	@Test
	public void should_load_all_user_tasks_with_jpql_and_sql() {
//		given
		createTask("some task", Priority.LOW);
//		when
        List<Task> tasks = jpaTaskRepository.selectTasksByUserName("some name");
        List<Task> tasksNative = jpaTaskRepository.selectTasksByUserNameNative("some name");
//		then
		assertThat(tasks).isEqualTo(tasksNative);
    }

	@Test
	public void should_filter_tasks_by_priority_with_criteria_api() {
//		given
		createTask("some task", Priority.LOW);
//		when
        Specification<Task> tasksByPriority = (root, query, cb) -> cb.equal(root.<Priority>get(Task_.priority), Priority.LOW);
        List<Task> tasks = jpaTaskRepository.findAll(tasksByPriority);
//		then
		assertThat(tasks.size()).isEqualTo(1);
    }

	@Test
	public void should_filter_tasks_by_priority_with_query_dsl() {
//		given
		createTask("some task", Priority.LOW);
//		when
		Predicate tasksByPriority = task.priority.eq(Priority.LOW);
		Iterable<Task> all = jpaTaskRepository.findAll(tasksByPriority);
//		then
		assertThat(size(all)).isEqualTo(1);
	}

	@Test
	public void could_be_more_readable() {
//		given
		createTask("some task", Priority.LOW);
//		when
        Specification<Task> tasksByUserName = (root, query, cb) -> {
			Join<Task, User> user = root.join(Task_.user);
			return cb.equal(user.<String>get(User_.name), "some name");
		};
        List<Task> tasks = jpaTaskRepository.findAll(tasksByUserName);
//		then
		assertThat(tasks.size()).isEqualTo(1);
    }

	@Test
	public void it_is_more_readable() {
//		given
		createTask("some task", Priority.LOW);
//		when
		Predicate tasksByPriority = task.user.name.eq("some name");
		Iterable<Task> all = jpaTaskRepository.findAll(tasksByPriority);
//		then
		assertThat(size(all)).isEqualTo(1);
	}

	@Test
	public void could_be_compilation_error() {
//		given
        Specification<Task> priority1 = (root, query, cb) -> cb.equal(root.<Priority>get(Task_.priority), 1);
        Specification<Task> priority2 = (root, query, cb) -> cb.equal(root.<Priority>get(Task_.priority), Priority.LOW);
		Specification<Task> name1 = (root, query, cb) -> cb.equal(root.<String>get(Task_.name), 1);
        Specification<Task> name2 = (root, query, cb) -> cb.equal(root.<String>get(Task_.name), "one");
        Specification<Task> id1 = (root, query, cb) -> cb.equal(root.<Integer>get(Task_.id), "one");
		Specification<Task> id2 = (root, query, cb) -> cb.equal(root.<Integer>get(Task_.id), 1);
	}

	@Test
	public void it_is_compilation_error() {
//		given
//		QTask.task.priority.eq(1);
//		QTask.task.priority.eq(Priority.LOW);
//		QTask.task.name.eq(1);
//		QTask.task.name.eq("one");
//		QTask.task.id.eq("one");
//		QTask.task.id.eq(1);
    }

	@Test
	public void should_filter_tasks_by_user_name_spring_data() {
//		given
		createTask("some task", Priority.LOW);
//		when
        assertThatThrownBy(() -> jpaTaskRepository.findByUserName(1)).isInstanceOf(InvalidDataAccessApiUsageException.class);
	}

    @Test
    public void should_filter_tasks_by_priority_with_projection_in_query_dsl() {
//		given
        createTask("some task", Priority.LOW);
//		when
        Predicate predicates = task.priority.eq(Priority.LOW).and(task.user.name.eq("some name"));
        List<Task> all = jpaTaskRepository.findWithProjection(predicates);
//		then
        assertThat(size(all)).isEqualTo(1);
    }

	private void createTask(String task_name, Priority priority) {
		Task task = Task.builder()
				.name(task_name)
				.user(jpaUserRepository.findByName("some name"))
				.priority(priority)
				.build();
		jpaTaskRepository.save(task);
	}

}
