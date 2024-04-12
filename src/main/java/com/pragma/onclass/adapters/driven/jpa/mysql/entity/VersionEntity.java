package com.pragma.onclass.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "version")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private  LocalDate startDate;


    private LocalDate endDate;


    private  Integer maximumQuota;

    @ManyToOne
    @JoinColumn(name = "id_bootcamp")
    private BootcampEntity bootcamp;



}
