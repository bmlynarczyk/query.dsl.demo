package com.impaqgroup.query.dsl.demo.jdbc;

import com.impaqgroup.query.dsl.demo.config.JdbcConfig;
import com.impaqgroup.query.dsl.demo.config.JpaConfig;
import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jdbc.Task;
import com.impaqgroup.query.dsl.demo.repository.jdbc.JdbcTaskRepository;
import com.impaqgroup.query.dsl.demo.repository.jdbc.JdbcUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JdbcConfig.class)
public class SpringJdbcTests {

	@Autowired
	private JdbcTaskRepository jdbcTaskRepository;

	@Autowired
	private JdbcUserRepository jdbcUserRepository;

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
				.user(jdbcUserRepository.findByName("some name"))
				.priority(priority)
				.build();
	}

}
