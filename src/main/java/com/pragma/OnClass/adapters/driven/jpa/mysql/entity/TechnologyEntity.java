package com.pragma.OnClass.adapters.driven.jpa.mysql.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "technology")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TechnologyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 90)
    private String description;

}
