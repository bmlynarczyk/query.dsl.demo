package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.Application;
import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaTaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpringJpaTests {

	@Autowired
	private JpaTaskRepository jpaTaskRepository;

	@Autowired
	private JpaUserRepository jpaUserRepository;

	@Test
	public void should_load_all_tasks() {
//		given
		Task persistableTask = createTask("some task", Priority.LOW);
//		when
		jpaTaskRepository.save(persistableTask);
		List<Task> loadedTasks = jpaTaskRepository.findAll();
//		then
		Assertions.assertThat(loadedTasks.size()).isEqualTo(1);
	}

	private Task createTask(String task_name, Priority priority) {
		return Task.builder()
				.name(task_name)
				.assignee(jpaUserRepository.findOne(1))
				.priority(priority)
				.build();
	}

}
