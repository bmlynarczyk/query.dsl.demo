package com.impaqgroup.query.dsl.demo.model.jpa;


import com.impaqgroup.query.dsl.demo.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Priority priority;

}
