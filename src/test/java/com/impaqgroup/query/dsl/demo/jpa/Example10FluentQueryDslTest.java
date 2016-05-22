package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.google.common.collect.Iterables.size;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example10FluentQueryDslTest extends JpaExampleBase {

	@Test
	public void it_is_much_more_readable() {
//		given
		createTask("some task1", Priority.LOW);
		createTask("some task2", Priority.HIGH);
//		when
		Iterable<Task> all = jpaTaskRepository.selectTasksQueryDsl(Priority.LOW, "some user", "some%");
//		then
		assertThat(size(all)).isEqualTo(1);
	}


}
