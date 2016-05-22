package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.model.jpa.Task_;
import com.impaqgroup.query.dsl.demo.model.jpa.User;
import com.impaqgroup.query.dsl.demo.model.jpa.User_;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;

import static com.google.common.collect.Iterables.size;
import static com.impaqgroup.query.dsl.demo.model.jpa.QTask.task;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example08CriteriaApiFailTest extends JpaExampleBase {

	@Test
	public void could_be_more_readable() {
//		given
		createTask("some task", Priority.LOW);
		createTask("some task", Priority.HIGH);
//		when
		Specification<Task> predicate = (root, query, criteriaBuilder) -> {
			Predicate byPriority = criteriaBuilder.equal(root.<Priority>get(Task_.priority), Priority.LOW);
			Join<Task, User> user = root.join(Task_.user);
			Predicate byUserName = criteriaBuilder.equal(user.<String>get(User_.name), "some user");
			return criteriaBuilder.and(byPriority, byUserName);
		};
		List<Task> tasks = jpaTaskRepository.findAll(predicate);
//		then
		assertThat(tasks.size()).isEqualTo(1);
	}

}
