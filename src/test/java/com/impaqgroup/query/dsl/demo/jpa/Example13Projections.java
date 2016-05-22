package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.mysema.query.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.google.common.collect.Iterables.size;
import static com.impaqgroup.query.dsl.demo.model.jpa.QTask.task;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example13Projections extends JpaExampleBase {

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


}
