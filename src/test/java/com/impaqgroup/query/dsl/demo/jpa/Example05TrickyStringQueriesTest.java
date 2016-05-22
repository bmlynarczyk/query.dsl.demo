package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example05TrickyStringQueriesTest extends JpaExampleBase {

	@Test
	public void difficult_to_refactor_queries() {
		assertThatThrownBy(() -> jpaTaskRepository.selectTasksJpql(Priority.LOW, "some name", "some name"))
				.isInstanceOf(RuntimeException.class);
		;
    }

}
