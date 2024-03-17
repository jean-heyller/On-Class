package com.pragma.OnClass.adapters.driven.jpa.mysql.repository;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITechnologyRepository extends JpaRepository<TechnologyEntity,Long> {
    Optional<TechnologyEntity> findByNameContaining(String name);
    Optional<TechnologyEntity> findByName(String name);
}