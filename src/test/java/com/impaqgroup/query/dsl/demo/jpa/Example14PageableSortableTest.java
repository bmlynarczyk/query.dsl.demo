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
public class Example14PageableSortableTest extends JpaExampleBase {

	@Test
	public void should_filter_tasks_by_priority_with_paging_and_sort_in_query_dsl() {
//		given
		createTask("some task1", Priority.LOW);
		createTask("some task2", Priority.LOW);
//		when
		Predicate predicates = task.priority.eq(Priority.LOW).and(task.user.name.eq("some user"));
		List<Task> all = jpaTaskRepository.findWithProjection(predicates, new PageRequest(0, 1));
//		then
		assertThat(size(all)).isEqualTo(1);
		assertThat(all.get(0).getName()).isEqualTo("some task2");
	}

}
