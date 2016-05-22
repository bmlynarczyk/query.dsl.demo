package com.impaqgroup.query.dsl.demo.model.jpa;

import com.impaqgroup.query.dsl.demo.model.Priority;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Task.class)
public abstract class Task_ {

	public static volatile SingularAttribute<Task, String> name;
	public static volatile SingularAttribute<Task, Integer> id;
	public static volatile SingularAttribute<Task, Priority> priority;
	public static volatile SingularAttribute<Task, User> user;

}

