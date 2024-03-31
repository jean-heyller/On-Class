package com.pragma.OnClass.adapters.driven.jpa.mysql.repository;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.BootCampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBootCampRepository extends JpaRepository<BootCampEntity,Long> {
    Optional<BootCampEntity> findByName(String name);
    Optional<BootCampEntity> findById(Long id);

    Page<BootCampEntity> findAll(Pageable pageable);

}
