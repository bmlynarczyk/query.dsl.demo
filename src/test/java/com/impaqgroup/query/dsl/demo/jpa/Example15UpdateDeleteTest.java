package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.mysema.query.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.google.common.collect.Iterables.size;
import static com.impaqgroup.query.dsl.demo.model.jpa.QTask.task;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example15UpdateDeleteTest extends JpaExampleBase {

	@Test
	public void should_update_tasks_by_name_in_query_dsl() {
//		given
		createTask("some task1", Priority.LOW);
		createTask("some task2", Priority.LOW);
//		when
		Predicate predicates = task.name.like("some%");
		long updatedCount = jpaTaskRepository.updatePriority(Priority.HIGH, predicates);
//		then
		assertThat(updatedCount).isEqualTo(2);
	}

	@Test
	public void should_delete_tasks_by_name_in_query_dsl() {
//		given
		createTask("some task1", Priority.LOW);
		createTask("some task2", Priority.LOW);
//		when
		Predicate predicates = task.name.like("some%");
		long updatedCount = jpaTaskRepository.delete(predicates);
//		then
		assertThat(updatedCount).isEqualTo(2);
	}


}
