package com.pragma.onclass.adapters.driven.jpa.mysql.repository;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface IVersionRepository extends JpaRepository<VersionEntity, Long> {
   Optional<VersionEntity> findById(Long id);

   VersionEntity findByStartDateAndEndDateAndMaximumQuotaAndBootcamp(Date startDate, Date endDate, Integer maximumQuota, BootcampEntity bootcamp);

}
