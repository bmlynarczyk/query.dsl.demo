package com.impaqgroup.query.dsl.demo.model.jpa;


import com.impaqgroup.query.dsl.demo.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User assignee;

    @Column
    @Enumerated(EnumType.STRING)
    private Priority priority;

}
