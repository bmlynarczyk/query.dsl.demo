package com.impaqgroup.query.dsl.demo.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class Example04StringQueriesTest extends JpaExampleBase {

	@Test
	public void difficult_to_refactor_queries() {
		jpaTaskRepository.selectTasksByUserName("some name");
		jpaUserRepository.findUserNames();
    }

}
