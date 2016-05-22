package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.google.common.collect.Iterables.size;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example12SpringDataRepositoryTest extends JpaExampleBase {

//	@Test
//	public void should_filter_tasks_by_user_name_with_wrong_property_spring_data() {
//		jpaTaskRepository.selectTasksByUserNameWrongQuery("some name");
//		jpaTaskRepository.findByUserNamee("some name");
//	}

	@Test
	public void should_filter_tasks_by_user_name_spring_data() {
//		given
		createTask("some task", Priority.LOW);
//		when then
		assertThatThrownBy(() -> jpaTaskRepository.findByUserName(1)).isInstanceOf(InvalidDataAccessApiUsageException.class);
	}


}
