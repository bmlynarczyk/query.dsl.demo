package com.impaqgroup.query.dsl.demo.jpa;

import com.impaqgroup.query.dsl.demo.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
}
