package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.model.jpa.Task_;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example07CriteriaApiRepositoryTest extends JpaExampleBase {

	@Test
	public void should_filter_tasks_by_priority_with_criteria_api() {
//		given
		createTask("some task 1", Priority.LOW);
		createTask("some task 2", Priority.HIGH);
//		when
        Specification<Task> tasksByPriority = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Priority>get(Task_.priority), Priority.LOW);
        List<Task> tasks = jpaTaskRepository.findAll(tasksByPriority);
//		then
		assertThat(tasks.size()).isEqualTo(1);
    }

}
