package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.config.JpaConfig;
import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.model.jpa.User;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaTaskRepository;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaConfig.class)
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
        List<Task> tasks = jpaTaskRepository.selectTasksByUserName("some name");
        List<Task> tasksNative = jpaTaskRepository.selectTasksByUserNameNative("some name");
//		then
		assertThat(tasks).isEqualTo(tasksNative);
    }

	private Task createTask(String task_name, Priority priority) {
		return Task.builder()
				.name(task_name)
				.user(jpaUserRepository.findByName("some name"))
				.priority(priority)
				.build();
	}

}
