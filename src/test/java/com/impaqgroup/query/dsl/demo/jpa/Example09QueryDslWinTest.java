package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.mysema.query.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.google.common.collect.Iterables.size;
import static com.impaqgroup.query.dsl.demo.model.jpa.QTask.task;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example09QueryDslWinTest extends JpaExampleBase {

	@Test
	public void it_is_more_readable() {
//		given
		createTask("some task", Priority.LOW);
		createTask("some task", Priority.HIGH);
//		when
		Predicate predicate = task.priority.eq(Priority.LOW).and(task.user.name.eq("some user"));
		Iterable<Task> all = jpaTaskRepository.findAll(predicate);
//		then
		assertThat(size(all)).isEqualTo(1);
	}


}
