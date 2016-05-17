package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.config.JpaConfig;
import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.model.jpa.Task_;
import com.impaqgroup.query.dsl.demo.model.jpa.User;
import com.impaqgroup.query.dsl.demo.model.jpa.User_;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaTaskRepository;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaConfig.class)
public class SpringJpaTests {

	@Autowired
	private JpaTaskRepository jpaTaskRepository;

	@Autowired
	private JpaUserRepository jpaUserRepository;

	@Test
	public void should_load_all_user_tasks_with_jpql_and_sql() {
//		given
		Task persistableTask = createTask("some task", Priority.LOW);
//		when
		jpaTaskRepository.save(persistableTask);
        List<Task> tasks = jpaTaskRepository.selectTasksByUserName("some name");
        List<Task> tasksNative = jpaTaskRepository.selectTasksByUserNameNative("some name");
//		then
		assertThat(tasks).isEqualTo(tasksNative);
    }

	@Test
	public void should_filter_tasks_by_priority_with_criteria_api() {
//		given
		Task persistableTask = createTask("some task", Priority.LOW);
//		when
		jpaTaskRepository.save(persistableTask);
        Specification<Task> tasksByPriority = (root, query, cb) -> cb.equal(root.<Priority>get(Task_.priority), Priority.LOW);
        List<Task> tasks = jpaTaskRepository.findAll(tasksByPriority);
//		then
		assertThat(tasks.size()).isGreaterThan(0);
    }

	@Test
	public void could_be_more_readable() {
//		given
		Task persistableTask = createTask("some task", Priority.LOW);
//		when
		jpaTaskRepository.save(persistableTask);
        Specification<Task> tasksByPriority = (root, query, cb) -> {
			Join<Task, User> user = root.join(Task_.user);
			return cb.equal(user.<String>get(User_.name), "some name");
		};
        List<Task> tasks = jpaTaskRepository.findAll(tasksByPriority);
//		then
		assertThat(tasks.size()).isGreaterThan(0);
    }

	@Test
	public void could_be_compilation_error() {
//		given
		Task persistableTask = createTask("some task", Priority.LOW);
//		when
		jpaTaskRepository.save(persistableTask);
        Specification<Task> tasksByName = (root, query, cb) -> cb.equal(root.<Priority>get(Task_.priority), "wrong parameter");
//		then
		assertThatThrownBy(() -> jpaTaskRepository.findAll(tasksByName)).isInstanceOf(InvalidDataAccessApiUsageException.class);
    }

	private Task createTask(String task_name, Priority priority) {
		return Task.builder()
				.name(task_name)
				.user(jpaUserRepository.findByName("some name"))
				.priority(priority)
				.build();
	}

}
