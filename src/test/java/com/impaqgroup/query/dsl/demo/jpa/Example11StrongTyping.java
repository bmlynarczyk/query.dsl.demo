package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.QTask;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.model.jpa.Task_;
import org.springframework.data.jpa.domain.Specification;

public class Example11StrongTyping {

	public void could_be_compilation_error() {
		Specification<Task> priority1 = (root, query, cb) -> cb.equal(root.<Priority>get(Task_.priority), 1);
		Specification<Task> priority2 = (root, query, cb) -> cb.equal(root.<Priority>get(Task_.priority), Priority.LOW);
		Specification<Task> name1 = (root, query, cb) -> cb.equal(root.<String>get(Task_.name), 1);
		Specification<Task> name2 = (root, query, cb) -> cb.equal(root.<String>get(Task_.name), "one");
		Specification<Task> id1 = (root, query, cb) -> cb.equal(root.<Integer>get(Task_.id), "one");
		Specification<Task> id2 = (root, query, cb) -> cb.equal(root.<Integer>get(Task_.id), 1);
	}

	public void it_is_compilation_error() {
//		QTask.task.priority.eq(1);
//		QTask.task.priority.eq(Priority.LOW);
//		QTask.task.name.eq(1);
//		QTask.task.name.eq("one");
//		QTask.task.id.eq("one");
//		QTask.task.id.eq(1);
	}


}
