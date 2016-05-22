package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example03JpqlTest extends JpaExampleBase{

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

}
