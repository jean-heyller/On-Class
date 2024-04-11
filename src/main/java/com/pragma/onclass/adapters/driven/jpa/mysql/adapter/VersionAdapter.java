package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.utils.exceptions.ValueAlreadyExitsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {

    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;

    private final IBootCampEntityMapper bootCampEntityMapper;

    private final IBootcampRepository bootCampRepository;
    @Override
    public void saveVersion(Version version) {
        if(bootCampRepository.findById(version.getBootcamp().getId()).isEmpty()){
            throw new NoDataFoundException();
        }

        BootcampEntity bootcampEntity = bootCampRepository.findById(version.getBootcamp().getId()).get();

        VersionEntity existingVersion = versionRepository.findByStartDateAndEndDateAndMaximumQuotaAndBootcamp(
                version.getStartDate(), version.getEndDate(), version.getMaximumQuota(), bootcampEntity);

        if (existingVersion != null) {
            String message = "The version";
            throw new ValueAlreadyExitsException(message);
        }
        version.setBootcamp(bootCampEntityMapper.toModel(bootcampEntity));
        versionRepository.save(versionEntityMapper.toEntity(version));

    }
}
