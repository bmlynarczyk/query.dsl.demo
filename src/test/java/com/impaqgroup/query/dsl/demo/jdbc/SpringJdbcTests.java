package com.impaqgroup.query.dsl.demo.jdbc;

import com.impaqgroup.query.dsl.demo.Application;
import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jdbc.Task;
import com.impaqgroup.query.dsl.demo.model.jdbc.User;
import com.impaqgroup.query.dsl.demo.repository.JdbcTaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpringJdbcTests {

	@Autowired
	private JdbcTaskRepository jdbcTaskRepository;

	@Test
	public void should_load_all_tasks() {
//		given
		Task persistableTask = createTask("some task", Priority.LOW);
//		when
		jdbcTaskRepository.save(persistableTask);
		List<Task> loadedTasks = jdbcTaskRepository.findAll();
//		then
		Assertions.assertThat(loadedTasks.size()).isEqualTo(1);
	}

	private Task createTask(String task_name, Priority priority) {
		return Task.builder()
				.id(1)
				.name(task_name)
				.assignee(User.builder()
						.id(1)
						.build())
				.priority(priority)
				.build();
	}

}
