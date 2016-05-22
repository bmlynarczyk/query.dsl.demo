package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.config.JpaConfig;
import com.impaqgroup.query.dsl.demo.model.Priority;
import com.impaqgroup.query.dsl.demo.model.jpa.Task;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaTaskRepository;
import com.impaqgroup.query.dsl.demo.repository.jpa.JpaUserRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = JpaConfig.class)
public class JpaExampleBase {

    @Autowired
    protected JpaTaskRepository jpaTaskRepository;

    @Autowired
    protected JpaUserRepository jpaUserRepository;

    @Before
    public void setUp(){
        jpaTaskRepository.deleteAll();
    }

    protected void createTask(String task_name, Priority priority) {
        Task task = Task.builder()
                .name(task_name)
                .user(jpaUserRepository.findByName("some user"))
                .priority(priority)
                .build();
        jpaTaskRepository.save(task);
    }

}
